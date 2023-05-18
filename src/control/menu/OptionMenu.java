package control.menu;

import control.Launcher;

public class OptionMenu extends Menu{

    public OptionMenu() {
        Button easy = new Button(10,10,50,40);
        Button medium = new Button(10,60,50,40);
        Button hard = new Button(10,120,50,40);

        Button back = new Button(10, 180, 50,40);
        easy.setText("Easy");
        medium.setText("Medium");
        hard.setText("Hard");
        back.setText("Back");
        easy.addCommand(() -> Launcher.gameSpeed = 40);
        medium.addCommand(() -> Launcher.gameSpeed= 35);
        hard.addCommand(() -> Launcher.gameSpeed= 27);
        back.addCommand(() -> Launcher.openMenu(new MainMenu()));
        buttons.add(easy);
        buttons.add(medium);
        buttons.add(hard);
        buttons.add(back);

    }
}
