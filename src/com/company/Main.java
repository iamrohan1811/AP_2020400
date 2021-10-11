package com.company;

import java.util.*;

class Citizen{
    private String name;
    private int age;
    private long unique_ID;
    private String vaccine_given;
    private int no_of_doses_given=0;
    private int next_due;
    private String vaccination_status;
    public Citizen(String name,int age,long u_id){
        this.name=name;
        this.age=age;
        this.unique_ID=u_id;
        this.vaccination_status="REGISTERED";
    }
    public void print_details(){
        System.out.println("Citizen Name: "+this.name+", Age: "+this.age+", Unique ID: "+this.unique_ID);
    }
    public void vaccinate(String vaccine_given,int due,int tot_dose){
        this.vaccine_given=vaccine_given;
        this.no_of_doses_given++;
        this.next_due=due;
        if(this.no_of_doses_given>0){
            this.vaccination_status="PARTIALLY VACCINATED";
        }
        if(this.no_of_doses_given==tot_dose){
            this.vaccination_status="FULLY VACCINATED";
            this.next_due=0;
        }
    }

    public void status(){
        System.out.println(this.vaccination_status);
        if(Objects.equals(this.vaccination_status, "FULLY VACCINATED")){
            System.out.println("Vaccine Given: "+this.vaccine_given);
            System.out.println("Number of Doses given: "+this.no_of_doses_given);
        }
        else if(Objects.equals(this.vaccination_status, "PARTIALLY VACCINATED")){
            System.out.println("Vaccine Given: "+this.vaccine_given);
            System.out.println("Number of Doses given: "+this.no_of_doses_given);
            System.out.println("Next due date: "+this.next_due);
        }
        else if(Objects.equals(this.vaccination_status, "REGISTERED")){

        }
    }
    public String getName(){
        return name;
    }

    public long getUnique_ID() {
        return unique_ID;
    }

    public int getNext_due() {
        return next_due;
    }
}
class Hospital{
    private String name;
    private int pin;
    private int h_id;
    static int count=100000;
    public Hospital(String name,int pin){
        this.name=name;
        this.pin=pin;
        this.h_id=count;
        count++;
    }
    public void print_details(){
        System.out.println("Hospital Name: "+this.name+", PinCode: "+this.pin+", Unique ID: "+this.h_id);
    }

    public String getName(){
        return name;
    }

    public int getPin(){
        return pin;
    }

    public int getH_id() {
        return h_id;
    }
}
class Vaccine{
    private String name;
    private int total_doses;
    private int gap;
    public Vaccine(String name,int doses,int gap){
        this.name=name;
        this.total_doses=doses;
        this.gap=gap;
    }

    public void print_details(){
        System.out.println("Vaccine Name: "+this.name+",Number of Doses: "+this.total_doses+", Gap between Doses:"+this.gap);
    }

    public String getName(){
        return name;
    }

    public int getTotal_doses() {
        return total_doses;
    }

    public int getGap() {
        return gap;
    }
}

class Slots{
    private int Hospital_id;
    private int day_no;
    private int qty;
    private String vaccine;
    public Slots(int id,int day_no,int qty,String vaccine){
        this.Hospital_id=id;
        this.day_no=day_no;
        this.qty=qty;
        this.vaccine=vaccine;
    }
    public void print_details(){
        System.out.println("Slot added by Hospital "+this.Hospital_id+" for Day: "+this.day_no+", Available Quantity: "+this.qty+" of Vaccine "+this.vaccine);
    }

    public int getHospital_id() {
        return Hospital_id;
    }

    public int getDay_no() {
        return day_no;
    }

    public String getVaccine(){
        return vaccine;
    }

    public int getQty() {
        return qty;
    }


    public void setQty(int qty){
        this.qty=qty;
    }

}


public class Main {

    public static void main(String[] args) {
	// write your code here
        List<Vaccine> vaccineList=new ArrayList<Vaccine>();
        List<Hospital> hospitalList=new ArrayList<Hospital>();
        List<Citizen> citizenList=new ArrayList<Citizen>();
        List<Slots> slotList=new ArrayList<Slots>();
        System.out.println("CoWin Portal initialized....");
        int choice=0;
        while(choice!=8){
            Scanner scn=new Scanner(System.in);
            System.out.println("---------------------------------");
            System.out.print("1. Add Vaccine\n2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination\n5. Book Slot for Vaccination\n6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit\n");
            choice=scn.nextInt();
            scn.nextLine();
            if(choice==1){
                System.out.print("Vaccine Name: ");
                String v_name=scn.nextLine();
                System.out.print("Number of Doses: ");
                int v_doses=scn.nextInt();
                int v_gap;
                if(v_doses>1){
                    System.out.print("Gap between doses: ");
                    v_gap=scn.nextInt();
                }
                else{
                    v_gap=0;
                }
                Vaccine V=new Vaccine(v_name,v_doses,v_gap);
                V.print_details();
                vaccineList.add(V);
            }
            else if(choice==2){
                System.out.print("Hospital Name: ");
                String H_name=scn.nextLine();
                System.out.print("Pincode: ");
                int H_pin=scn.nextInt();
                Hospital H=new Hospital(H_name,H_pin);
                H.print_details();
                hospitalList.add(H);
            }
            else if(choice==3){
                System.out.print("Citizen Name: ");
                String C_name=scn.nextLine();
                System.out.print("Age: ");
                int C_age=scn.nextInt();
                System.out.print("Unique ID: ");
                long C_uid=scn.nextLong();
                if(C_age>=18){
                    Citizen C=new Citizen(C_name,C_age,C_uid);
                    C.print_details();
                    citizenList.add(C);
                }
                else{
                    System.out.println("Only above 18 are allowed");
                }
            }
            else if(choice==4){   //add slot for vaccination
                System.out.print("Enter Hospital ID: ");
                int hosp_id=scn.nextInt();
                System.out.print("Enter number of slots to be added: ");
                int no_of_slots=scn.nextInt();
                for(int i=0;i<no_of_slots;i++){
                    System.out.print("Enter Day Number: ");
                    int day_no=scn.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty=scn.nextInt();
                    System.out.println("Select Vaccine: ");
                    for(int j=0;j<vaccineList.size();j++){
                        System.out.println(j+". "+ vaccineList.get(j).getName());
                    }
                    int vaccine_select=scn.nextInt();
                    Slots S=new Slots(hosp_id,day_no,qty,vaccineList.get(vaccine_select).getName());
                    S.print_details();
                    slotList.add(S);
                }
            }
            else if(choice==5){    //book slot for a citizen
                System.out.print("Enter patient unique ID: ");
                long p_id=scn.nextLong();
                int p_idx=0;
                for(int i=0;i<citizenList.size();i++){
                    if(citizenList.get(i).getUnique_ID()==p_id){
                        p_idx=i;
                        break;
                    }
                }
                System.out.print("1. Search by area\n" +
                        "2. Search by Vaccine\n" +
                        "3. Exit\n");
                System.out.print("Enter option: ");
                int option=scn.nextInt();
                if(option==1){
                    System.out.print("Enter pincode: ");
                    int pin=scn.nextInt();
                    for(int i=0;i<hospitalList.size();i++){
                        if(hospitalList.get(i).getPin()==pin){
                            System.out.println(hospitalList.get(i).getH_id()+" "+hospitalList.get(i).getName());
                        }
                    }
                    boolean flag_slot=false;
                    System.out.print("Enter Hospital id: ");
                    int h_id= scn.nextInt();
                    for(int i=0;i<slotList.size();i++){
                        if(slotList.get(i).getHospital_id()==h_id && slotList.get(i).getDay_no()>=citizenList.get(p_idx).getNext_due()){
                            System.out.println(i+"-> Day: "+slotList.get(i).getDay_no()+" Available Qty:"+slotList.get(i).getQty()+" Vaccine:"+slotList.get(i).getVaccine());
                            flag_slot=true;
                        }
                    }
                    if(flag_slot==true){
                        System.out.print("Choose slot: ");
                        int select_slot=scn.nextInt();
                        System.out.println(citizenList.get(p_idx).getName()+" vaccinated with "+slotList.get(select_slot).getVaccine());
                        //slotList.get(select_slot).qty--;
                        slotList.get(select_slot).setQty(slotList.get(select_slot).getQty()-1);
                        int V_idx=0;
                        for(int i=0;i<vaccineList.size();i++){
                            if(Objects.equals(slotList.get(select_slot).getVaccine(), vaccineList.get(i).getName())){
                                V_idx=i;
                                break;
                            }
                        }
                        citizenList.get(p_idx).vaccinate(slotList.get(select_slot).getVaccine(),slotList.get(select_slot).getDay_no()+vaccineList.get(V_idx).getGap(),vaccineList.get(V_idx).getTotal_doses()); //working on it

                    }
                    else{
                        System.out.println("No slots available");

                    }
                }
                else if(option==2){
                    scn.nextLine();
                    System.out.print("Enter vaccine name: ");
                    String vacc=scn.nextLine();
                    int s_idx=0;// have to see this
                    for(int i=0;i<hospitalList.size();i++){
                        for(int j=0;j<slotList.size();j++){
                            if(Objects.equals(slotList.get(j).getVaccine(), vacc) && slotList.get(j).getHospital_id()==hospitalList.get(i).getH_id()){
                                System.out.println(hospitalList.get(i).getH_id()+" "+hospitalList.get(i).getName());
                            }
                        }
                    }
//                    for(int i=0;i<slotList.size();i++){
//                        if(Objects.equals(slotList.get(i).getVaccine(), vacc)){
//                            for(int j=0;j<hospitalList.size();j++){
//                                if(hospitalList.get(j).getH_id()==slotList.get(i).getHospital_id()){
//                                    System.out.println(hospitalList.get(j).getH_id()+" "+hospitalList.get(j).getName());
//                                }
//                            }
//                        }
//                    }
                    System.out.print("Enter Hospital id: ");
                    int h_id= scn.nextInt();
                    boolean slot_flag=false;
                    for(int i=0;i<slotList.size();i++){
                        if(slotList.get(i).getHospital_id()==h_id && Objects.equals(slotList.get(i).getVaccine(), vacc) && slotList.get(i).getDay_no()>=citizenList.get(p_idx).getNext_due()){
                            System.out.println(i+"-> Day: "+slotList.get(i).getDay_no()+" Available Qty:"+slotList.get(i).getQty()+" Vaccine:"+slotList.get(i).getVaccine());
                            slot_flag=true;
                        }
                    }
                    if(slot_flag==true){
                        System.out.print("Choose slot: ");
                        int select_slot=scn.nextInt();
                        System.out.println(citizenList.get(p_idx).getName()+" vaccinated with "+slotList.get(select_slot).getVaccine());
                        //slotList.get(select_slot).qty--;
                        slotList.get(select_slot).setQty(slotList.get(select_slot).getQty()-1);
                        int V_idx=0;
                        for(int i=0;i<vaccineList.size();i++){
                            if(Objects.equals(slotList.get(select_slot).getVaccine(), vaccineList.get(i).getName())){
                                V_idx=i;
                                break;
                            }
                        }
                        citizenList.get(p_idx).vaccinate(slotList.get(select_slot).getVaccine(),slotList.get(select_slot).getDay_no()+vaccineList.get(V_idx).getGap(),vaccineList.get(V_idx).getTotal_doses()); //working on it
                    }
                    else{
                        System.out.println("No slots available");
                    }

                }
                else if(option ==3){
                }
            }
            else if(choice==6){
                System.out.print("Enter Hospital ID: ");
                int h_id=scn.nextInt();
                for(int i=0;i<slotList.size();i++){
                    if(slotList.get(i).getHospital_id()==h_id){
                        System.out.println("Day: "+slotList.get(i).getDay_no()+" Vaccine: "+slotList.get(i).getVaccine()+" Available Qty: "+slotList.get(i).getQty());
                    }
                }
            }
            else if(choice ==7){
                System.out.print("Enter Patient ID: ");
                long p_id=scn.nextLong();
                for(int i=0;i<citizenList.size();i++){
                    if(citizenList.get(i).getUnique_ID()==p_id){
                        citizenList.get(i).status();
                    }
                }

            }
            else if(choice ==8){
                break;
            }
        }

    }
}
