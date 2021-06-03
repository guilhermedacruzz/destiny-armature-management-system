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

    private AuthService authService;
    private ArmorRepository armorRepository;
    private ObservableList<ArmorSet> results;

    public ArmorSetRepositoryImpl(AuthService authService, ArmorRepository armorRepository) {
        this.authService = authService;
        this.armorRepository = armorRepository;
        results = FXCollections.observableArrayList();
    }

    private void a(Armor[] resultArmors, ObservableList<ObservableList<Armor>> observableList, int index) {
        if(index == 4) {
            results.add(new ArmorSet(resultArmors.clone(), false, false, false));
            return;
        }
        for(Armor armor: observableList.get(index)) {
            resultArmors[index] = armor;
            a(resultArmors, observableList, index + 1);
        }
    }

    private ObservableList<Armor> b(Armor exotic, String type) throws SQLException {
        int id = authService.getLogged().getId();
        String guardianClass = authService.getLogged().getGuardianClass();

        if(!exotic.getType().equals(type)) {
            ObservableList<Armor> armors = armorRepository.selectByType(id, guardianClass, type);
            if(!armors.equals(FXCollections.observableArrayList()))
                return armors;
        }

        throw new SQLException("Falta Armaduras para Realizar o Cálculo.");
    }

    @Override
    public ObservableList<ArmorSet> calculate(Armor exotic, boolean powerfulFriends, boolean radiantLight, boolean stasis) throws SQLException {
        ObservableList<ObservableList<Armor>> observableList = FXCollections.observableArrayList();

        observableList.add(b(exotic, "Capacete"));
        observableList.add(b(exotic, "Manopla"));
        observableList.add(b(exotic, "Armadura de Torso"));
        observableList.add(b(exotic, "Armadura de Perna"));
        observableList.add(b(exotic, "Item de Classe"));

        results.clear();

        Armor[] basicArmors = new Armor[5];
        basicArmors[4] = exotic;

        a(basicArmors, observableList, 0);

        return FXCollections.unmodifiableObservableList(results);
    }
}
