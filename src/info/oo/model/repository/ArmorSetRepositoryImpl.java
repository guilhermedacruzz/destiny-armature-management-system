package info.oo.model.repository;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ArmorSet;
import info.oo.model.Inventory;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.model.repository.interfaces.ArmorSetRepository;
import info.oo.services.AuthService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.stream.IntStream;

public class ArmorSetRepositoryImpl implements ArmorSetRepository {

    private static final short MAX_SET = 5;
    private AuthService authService;
    private ArmorRepository armorRepository;
    private ObservableList<ArmorSet> results;

    public ArmorSetRepositoryImpl(AuthService authService, ArmorRepository armorRepository) {
        this.authService = authService;
        this.armorRepository = armorRepository;
        results = FXCollections.observableArrayList();
    }

    private void scaleArmors(ArmorSet armorSet, ObservableList<ObservableList<Armor>> observableList, int index) {
        if(index == MAX_SET - 1) {
            results.add(new ArmorSet(armorSet));
            return;
        }

        for(Armor armor: observableList.get(index)) {
            armorSet.getArmors()[index] = armor;
            scaleArmors(armorSet, observableList, index + 1);
        }
    }

    private void loadArmors(ObservableList<ObservableList<Armor>> observableList , Armor exotic, String type) throws SQLException {
        int id = authService.getLogged().getId();
        String guardianClass = authService.getLogged().getGuardianClass();

        if(!exotic.getType().equals(type)) {
            ObservableList<Armor> armors = armorRepository.selectByType(id, guardianClass, type);
            if(!armors.equals(FXCollections.observableArrayList())) {
                observableList.add(armors);
            }
            else {
                throw new SQLException("Falta Armaduras para Realizar o Cálculo.");
            }
        }
    }

    @Override
    public ObservableList<ArmorSet> calculate(Armor exotic, boolean powerfulFriends, boolean radiantLight, boolean stasis, Inventory inventory) throws SQLException {
        ObservableList<ObservableList<Armor>> observableList = FXCollections.observableArrayList();

        loadArmors(observableList, exotic, "Capacete");
        loadArmors(observableList, exotic, "Manopla");
        loadArmors(observableList, exotic, "Armadura de Torso");
        loadArmors(observableList, exotic, "Armadura de Perna");
        loadArmors(observableList, exotic, "Item de Classe");

        results.clear();

        ArmorSet armorSetBasic = new ArmorSet(new Armor[MAX_SET], powerfulFriends, radiantLight, stasis);

        armorSetBasic.getArmors()[MAX_SET-1] = exotic;

        scaleArmors(armorSetBasic, observableList, 0);

        filterArmors(inventory);

        return FXCollections.unmodifiableObservableList(results);
    }

    private void filterArmors(Inventory inventory) {
        for(ArmorSet armorSet: results) {
            Inventory armorInventory = armorSet.generateSpent();

            if(armorInventory.getAscendentFragments() >= inventory.getAscendentFragments())
                results.remove(armorSet);

            int[] armorAttribute = armorSet.generateAttributes().getAttributesVetor();
            int sum = IntStream.of(armorAttribute).sum();

            if(sum <= 39)
                results.remove(armorSet);
        }
    }
}
