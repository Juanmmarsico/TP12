package main.control.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import main.model.Expense;
import main.model.Friend;

public class FriendController {
	OwnwerController ownwerController;
		String friendPath = "./Files/Friends.txt";
		
		public FriendController() {
			// TODO Auto-generated constructor stub
		}
		public FriendController(String source, OwnwerController ownwerController){
			friendPath = source;
			this.ownwerController = ownwerController;
			readFriends();
		}
		
		public void setOwnwerController(OwnwerController ownwerController) {
			this.ownwerController = ownwerController;
		}
	private ArrayList<Friend> readFriends() {
			// TODO Auto-generated method stub
			ArrayList<Friend> friends = new ArrayList<Friend>();
			try {
				Scanner scanner = new Scanner(new File(friendPath));
				while (scanner.hasNextLine()){
					String [] strings = scanner.nextLine().split(";");
					if (strings.length <= 3) {
						String name =strings[0];
						String lastName= strings[1];
						double adeuda =Double.parseDouble(strings[2]);
						addFriend(name, lastName, adeuda);

					}else {
						if (strings[3].equals(" ") || strings[3].equals("") || strings.length<=4) {
							String name =strings[1];
							String lastName= strings[2];
							double adeuda =Double.parseDouble(strings[3]);
							addFriend(name, lastName, adeuda);
						}else{
					String mail=strings[4];
					long celNumber = Long.parseLong(strings[5].equals(" ")?"0":strings[5]);
					String name =strings[1];
					String lastName= strings[2];
					double adeuda =Double.parseDouble(strings[3].equals(" ")?"0":strings[3]);
					addFriend(mail, celNumber, name, lastName, adeuda);
						}
					}
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return friends;
		}
	public void	writeFriends(){
		try {
			
			FileWriter fWriter = new FileWriter(new File(friendPath));
			if(!ownwerController.getOwner().getFriends().isEmpty()){
				for(Friend f:ownwerController.getOwner().getFriends()){
					if (!f.isHasChange()) {
						System.out.println("salio sin guardar amigos");
						return;
					}

				fWriter.write(f.writeDocument());
				}
//				ownwerController.getOwner().getFriends().get(ownwerController.getOwner().getFriends().size()-1).setHasChange();
Friend.resetHasChange();
				fWriter.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addFriend (String mail, long celNumber, String name, String lastName,double adeuda){
		ownwerController.getOwner().AddFriend(new Friend(mail, celNumber, name, lastName,adeuda));
	}
	public void addFriend (String name, String lastName, double adeuda){
	ownwerController.getOwner().AddFriend(new Friend(name, lastName,adeuda));
	}
	public void readFriends(File file) {
		// TODO Auto-generated method stub
		System.out.println(file.getName());
		friendPath = file.getPath();
		readFriends();
	}
	public Friend getFriend(String friend) {
		// TODO Auto-generated method stub
		for (Friend f : ownwerController.getOwner().getFriends()) {
			for (String fr : friend.split(" ")) {
				if (f.getName().equals(fr)) {
					return f;
				}
			}
		}
		String name = friend.split(" ")[0];
		String laString =friend.split(" ")[1];
		double value = Double.parseDouble(friend.split(" ")[2]);
		addFriend(name,laString , value);
		return getFriend(friend);
	}
}
