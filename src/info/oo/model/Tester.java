package info.oo.model;

import info.oo.model.repository.ArmorRepositoryImpl;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Tester {

    private AuthService authService;
    private ArmorRepository armorRepository;
    private ObservableList<Armor[]> results;

    public Tester(AuthService authService, ArmorRepository armorRepository) {
        this.authService = authService;
        this.armorRepository = armorRepository;
        results = FXCollections.observableArrayList();
    }

    public boolean resultCalculateArmors(Armor exotic,
                                         boolean powerfulFriends,
                                         boolean radiantLight,
                                         boolean stasis) throws SQLException {

        ObservableList<ObservableList<Armor>> observableList = FXCollections.observableArrayList();

        int id = authService.getLogged().getId();
        String guardianClass = authService.getLogged().getGuardianClass();

        if(!exotic.getType().equals("Capacete"))
            observableList.addAll(armorRepository.selectByType(id, guardianClass,"Capacete"));
        if(!exotic.getType().equals("Manopla"))
            observableList.addAll(armorRepository.selectByType(id, guardianClass,"Manopla"));
        if(!exotic.getType().equals("Armadura de Torso"))
            observableList.addAll(armorRepository.selectByType(id, guardianClass,"Armadura de Torso"));
        if(!exotic.getType().equals("Armadura de Perna"))
            observableList.addAll(armorRepository.selectByType(id, guardianClass,"Armadura de Perna"));
        if(!exotic.getType().equals("Item de Classe"))
            observableList.addAll(armorRepository.selectByType(id, guardianClass,"Item de Classe"));

        results.clear();

        Armor[] basic = new Armor[5];
        basic[4] = exotic;

        a(basic, observableList, 0);

        return true;
    }

    private void a(Armor[] resultArmors, ObservableList<ObservableList<Armor>> observableList, int index) {
        if(index == 4) {
            results.add(resultArmors.clone());
            return;
        }
        for(Armor armor: observableList.get(index)) {
            resultArmors[index] = armor;
            a(resultArmors, observableList, index + 1);
        }
    }

    public ObservableList<Armor[]> getResults() {
        return results;
    }

    public void setResults(ObservableList<Armor[]> results) {
        this.results = results;
    }
}
