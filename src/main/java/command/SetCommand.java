package command;

import application.GlobalParameter;

/**
 * Created by mustafa on 24.04.2017.
 */
public class SetCommand implements Command {
    public boolean execute(String[] parameter) {
        if(GlobalParameter.parameterMap.get(parameter[1]) == null) {
            System.out.println(parameter[1] + " isimli parametre yok");
        } else {
            try {
                int value = Integer.parseInt(parameter[2]);
                GlobalParameter.parameterMap.put(parameter[1], value);
            } catch (NumberFormatException ex) {
                System.out.println("Lütfen sayısal bir değer giriniz.");
                ex.printStackTrace();
                return false;
            }
        }

        return true;
    }
}
