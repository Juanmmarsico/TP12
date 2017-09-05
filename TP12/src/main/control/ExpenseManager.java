package main.control;

import main.GUI.mainWindow.Window.MainFrame;
import main.control.controllers.*;
public class ExpenseManager {
MainFrame mainFrame;
OwnwerController ownwerController;



public ExpenseManager(MainFrame mainFrame){
    this.mainFrame = mainFrame;
    ownwerController = new OwnwerController();
    ownwerController.readAll();
}

public OwnwerController getOwnwerController() {
	return ownwerController;
}

public void SaveAllValues() {
	// TODO Auto-generated method stub
	
	ownwerController.writeFiles();
}

public void updateFriendPanelList() {
	mainFrame.updateFriendList();
}

public MainFrame getMainFrame() {
	return mainFrame;
}
 

}
