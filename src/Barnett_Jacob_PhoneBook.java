//Project by Jacob Barnett

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Barnett_Jacob_PhoneBook {

    static Entry[] entry = new Entry[200];
    static int count = 0;

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        String command = "";
        String[] cmdArray = new String[2];
        String name = "";
        String phonenumber = "";
        String notes = "";
        readPhoneBook("PhoneBook.txt");

        System.out.println("Codes are entered as 1 to 8 characters.");
        System.out.println("Use 'e' for enter, 'f' for find, 'l' for list, 'q' for quit.\n");

        do {
            System.out.print("Command: ");
            command = scan.nextLine();
            cmdArray = command.split(" ");
            String index = "";
            String maxLength = "";

            if(cmdArray.length == 1 && (!cmdArray[0].equalsIgnoreCase("l") && !cmdArray[0].equalsIgnoreCase("q"))) {
                System.out.println("** Command not in proper format.\n");
                continue;
            }
            if(cmdArray.length == 2) {
                maxLength = cmdArray[1];
                if(maxLength.length() > 8) {
                    System.out.println("** Command not in proper format.\n");
                    continue;
                } else if(!cmdArray[0].equalsIgnoreCase("e") && !cmdArray[0].equalsIgnoreCase("f")) {
                    System.out.println("** Command not in proper format.\n");
                    continue;
                }
            }
            if(cmdArray.length > 2) {
                System.out.println("** Command not in proper format.\n");
                continue;
            }

            if(cmdArray[0].equalsIgnoreCase("e")) {
                name = cmdArray[1];
                System.out.print("Enter number: ");
                phonenumber = scan.nextLine();
                System.out.print("Enter notes: ");
                notes = scan.nextLine();
                System.out.println();

                Entry e = new Barnett_Jacob_PhoneBook().new Entry();
                e.setName(name);
                e.setPhonenumber(phonenumber);
                e.setNotes(notes);
                entry[count] = e;
                count++;
            } else if(cmdArray[0].equalsIgnoreCase("f")) {
                String searchCode = cmdArray[1];

                for(int i = 0;i < count;i++) {
                    if(entry[i].getName().equalsIgnoreCase(searchCode)) {
                        System.out.println("-- " + entry[i].getName());
                        System.out.println("-- " + entry[i].getPhonenumber());
                        System.out.println("-- " + entry[i].getNotes());
                        index = searchCode;
                        System.out.println();
                    }
                }
                if(index.compareTo(searchCode) != 0) {
                    System.out.println("** There is no code " + searchCode + "\n");
                }
            } else if(cmdArray[0].equalsIgnoreCase("l")) {
                listAllEntries();
            } else if(cmdArray[0].equalsIgnoreCase("q")) {
                writePhoneBook("PhoneBook.txt");
                break;
            }
        } while(true);
    }

    class Entry {
        String name;
        String phonenumber;
        String notes;

        public void setNotes(String c) {
            this.notes = c;
        }

        public String getNotes() {
            return this.notes;
        }

        public void setName(String n) {
            this.name = n;
        }

        public String getName() {
            return this.name;
        }

        public void setPhonenumber(String p) {
            this.phonenumber = p;
        }

        public String getPhonenumber() {
            return this.phonenumber;
        }
    }

    public static void readPhoneBook(String FileName) {
        File file = new File(FileName);
        if(file.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader("PhoneBook.txt"));
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] phone = line.split("\t");
                    Entry e = new Barnett_Jacob_PhoneBook().new Entry();
                    e.setNotes(phone[2]);
                    e.setPhonenumber(phone[1]);
                    e.setName(phone[0]);
                    entry[count++] = e;
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void listAllEntries() {
        if(count == 0) {
            System.out.println("** No entries in the phonebook. Please use 'e' command to enter.\n");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println("-- " + entry[i].getName());
                System.out.println("-- " + entry[i].getPhonenumber());
                System.out.println("-- " + entry[i].getNotes());
                System.out.println();
            }
        }
    }

    public static void writePhoneBook(String FileName) throws Exception {
        PrintStream P = new PrintStream(FileName);
        for (int i = 0; i < count; i++) {
            P.println(entry[i].name + "\t" + entry[i].phonenumber + "\t" + entry[i].notes);
        }
        P.close();
        System.out.println("** Phonebook stored.");
    }
}