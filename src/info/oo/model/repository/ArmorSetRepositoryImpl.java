package info.oo.model.repository;

import info.oo.model.Armor;
import info.oo.model.ArmorSet;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.model.repository.interfaces.ArmorSetRepository;
import info.oo.services.AuthService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

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

    private void a(ArmorSet armorSet, ObservableList<ObservableList<Armor>> observableList, int index) {
        if(index == MAX_SET - 1) {
            results.add(new ArmorSet(armorSet));
            return;
        }

        for(Armor armor: observableList.get(index)) {
            armorSet.getArmors()[index] = armor;
            a(armorSet, observableList, index + 1);
        }
    }

    private void b(ObservableList<ObservableList<Armor>> observableList , Armor exotic, String type) throws SQLException {
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
    public ObservableList<ArmorSet> calculate(Armor exotic, boolean powerfulFriends, boolean radiantLight, boolean stasis) throws SQLException {
        ObservableList<ObservableList<Armor>> observableList = FXCollections.observableArrayList();

        b(observableList, exotic, "Capacete");
        b(observableList, exotic, "Manopla");
        b(observableList, exotic, "Armadura de Torso");
        b(observableList, exotic, "Armadura de Perna");
        b(observableList, exotic, "Item de Classe");

        results.clear();

        ArmorSet armorSetBasic = new ArmorSet(new Armor[MAX_SET], powerfulFriends, radiantLight, stasis);

        armorSetBasic.getArmors()[MAX_SET-1] = exotic;

        a(armorSetBasic, observableList, 0);

        return FXCollections.unmodifiableObservableList(results);
    }
}
