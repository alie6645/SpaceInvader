package control.menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class MenuKeypad extends KeyAdapter {
    Menu menu;
    List<Button> buttons;
    int index = 0;
    Button active;
    public MenuKeypad(Menu menu){
        this.menu = menu;
        this.buttons = menu.buttons;
        active = buttons.get(0);
        active.toggle();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case ('s'):
                active.toggle();
                index++;
                if (index>=buttons.size()){
                    index=0;
                }
                active = buttons.get(index);
                active.toggle();
                break;
            case ('w'):
                active.toggle();
                index--;
                if (index<0){
                    index=buttons.size()-1;
                }
                active = buttons.get(index);
                active.toggle();
                break;
            case (' '):
                active.run();
        }
        menu.repaint();
    }
}
