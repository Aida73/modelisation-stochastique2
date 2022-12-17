package ept.stochastique.travailFinal;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import umontreal.ssj.simevents.Event;
import umontreal.ssj.simevents.Sim;

public class ReplayMonth {
	
	LinkedList<Customer>[] waitingLists = new LinkedList[8];
	
	ArrayList<Customer> custServed = new ArrayList<Customer>();
	
	private double[] tabLES = {0, 0, 0, 0, 0, 0, 0, 0};
	
	LinkedList<Customer> custInService = new LinkedList<Customer>();
	
	public ReplayMonth() {
		for(int i=0; i<8; i++)
			waitingLists[i] = new LinkedList<Customer>();
	}
	
	
	class EndService extends Event {
		
		private Customer cust;
		
		public EndService(Customer cust) {
			this.cust = cust;
		}
		
		public void actions() {
			if(custInService.contains(cust)) {
				custInService.remove(cust);
				custServed.add(cust);
			}
		}
	}
	
	class EnterInService extends Event {
		
		private Customer cust;
		
		public EnterInService(Customer cust) {
			this.cust = cust;
		}
		
		public void actions() {
			tabLES[getIndice(cust.serviceType)] = cust.waitingTime;
			waitingLists[getIndice(cust.serviceType)].remove(cust);
			custInService.addLast(cust);
			new EndService(cust).schedule(cust.endServiceTime-cust.beginServiceTime);
		}
		
	}
	
	// To complete
	public int getIndice(int serviceType) {
		if(serviceType==30175)
			return 0;
		if(serviceType==30560)
			return 1;
		if(serviceType==30172)
			return 2;
		if(serviceType==30181)
			return 3;
		if(serviceType==30179)
			return 4;
		if(serviceType==30066)
			return 5;
		if(serviceType==30241)
			return 6;
		return 7;

	}
	
	class Abandon extends Event{
		
		private Customer cust;
		
		public Abandon(Customer cust) {
			this.cust = cust;
		}
		@Override
		public void actions() {
			if(waitingLists[getIndice(cust.serviceType)].contains(cust))
				waitingLists[getIndice(cust.serviceType)].remove(cust);
		}
		
	}
	

	
	public void initialiseValuesQ(Customer cust) {
		cust.waintingLengthT1 = waitingLists[0].size();
		cust.waintingLengthT2 = waitingLists[1].size();
		cust.waintingLengthT3 = waitingLists[2].size();
		cust.waintingLengthT4 = waitingLists[3].size();
		cust.waintingLengthT5 = waitingLists[4].size();
		cust.waintingLengthT6 = waitingLists[5].size();
		cust.waintingLengthT7 = waitingLists[6].size();
		cust.waintingLengthT8 = waitingLists[7].size();
		cust.lES = tabLES[getIndice(cust.serviceType)];
	}
	
	class Arrival extends Event {
		
		private Customer cust;
		private ReplayMonth replayDay;
		
		public Arrival(Customer cust,ReplayMonth replayDay) {
			this.cust = cust;
			this.replayDay = replayDay;
		}

		@Override
		public void actions() {
			initialiseValuesQ(cust);
			cust.nomberOfAgents = custInService.size();
			if(cust.beginServiceTime!=-1.0 && 
					cust.beginServiceTime-cust.arrivalTime<=3) {
				cust.waitingTime = 0.0;
				new EnterInService(cust).schedule(0.0);
			}
			else {
				waitingLists[getIndice(cust.serviceType)].add(cust);
				if(cust.beginServiceTime==-1.0)
					new Abandon(cust).schedule(cust.endServiceTime-cust.arrivalTime);
				else {
					cust.waitingTime = cust.beginServiceTime-cust.arrivalTime;
					cust.queueSize = waitingLists[getIndice(cust.serviceType)].size();
					new EnterInService(cust).schedule(cust.waitingTime);
				}
			}
		}
		
	}
	
	class Customer {

		
		private double arrivalTime;
		private int serviceType;
		private double beginServiceTime;
		private double endServiceTime;
		private int queueSize = 0;
		
		private int waintingLengthT1, waintingLengthT2, waintingLengthT3, waintingLengthT4;
		private int waintingLengthT5, waintingLengthT6, waintingLengthT7, waintingLengthT8;
		
		private int nomberOfAgents;
		
		private double lES;
		
		private double waitingTime;
		
		private String arrivalDateTime;

	}
	

	
	public double getTime(String dateAndtime) {
		String dateAndTimes[] = dateAndtime.split(" ");
		String[] dates = dateAndTimes[0].split("-");
		String[] times = dateAndTimes[1].split(":");
		return ((Double.parseDouble(dates[2])-1)*86400)
				+Double.parseDouble(times[0])*3600
				+ Double.parseDouble(times[1])*60
				 + Double.parseDouble(times[2]);
	}
	

	// Decouper le jeu de donnes day by day
	public void readDay(String monthFile) throws IOException {
		System.out.println("begin reading");

		System.out.println("begin reading file"+new File("").getAbsolutePath() + "/calls-2014/" +monthFile);
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/calls-2014/" +monthFile));
		String lineReaded  = br.readLine();
		int j = 0;
		while(lineReaded != null) {
			if (j<=0) {
				j++;
				lineReaded  = br.readLine();
				continue;
			}
			if(lineReaded.equals("NULL")) {
				lineReaded  = br.readLine();
				continue;
			}
			lineReaded = lineReaded.replaceAll("\"", "");
			System.out.println(lineReaded);
			Customer cust = new Customer();
			String[] elts = lineReaded.split(",");
			if(elts[6].equals("NULL")) {
				lineReaded  = br.readLine();
				continue;
			}

			cust.arrivalTime = getTime(elts[0]);
			cust.serviceType = Integer.parseInt(elts[1]);
			if (!elts[3].equals("NULL")) {
				cust.beginServiceTime = getTime(elts[3]);
			}
			
			else
				cust.beginServiceTime = -1.0;
			
			
			cust.arrivalDateTime = new String(elts[0]);
			cust.endServiceTime = getTime(elts[6]);
			
			cust.waitingTime = cust.beginServiceTime-cust.arrivalTime;
			
			new Arrival(cust,this).schedule(cust.arrivalTime);
			
			lineReaded  = br.readLine();
		}
		System.out.println("end reading");

		br.close();
	}
	

	
	
	//Enregistrer la liste des clients servies dans un fichier
	public void writeDataInFile(String filename,ArrayList<Customer> l) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true));
		String XY="";
		bw.write("CustomerType,ArrivalTime,QueueSize,wl_30175,wl_30560,wl_30172,wl_30181,wl_30179,wl_30066,wl_30241,wl_others,NbAgents,LES,WaitingTime\n");
		for(Customer c : l) {
			XY += c.serviceType+","+
					//date receives c.arrivalDateTime+","+
					c.arrivalTime+","+
					c.queueSize+","+
					c.waintingLengthT1+","+
					c.waintingLengthT2+","+
					c.waintingLengthT3+","+
					c.waintingLengthT4+","+
					c.waintingLengthT5+","+
					c.waintingLengthT6+","+
					c.waintingLengthT7+","+
					c.waintingLengthT8+","+
					c.nomberOfAgents+","+
					c.lES+","+
					c.waitingTime + "\n";
			bw.write(XY);
			bw.newLine();
			XY = "";
		}
		bw.close();
	}
	
	public void simulateOneDay(String dayFile) throws IOException {
		Sim.init();
		readDay(dayFile);
		new EndOfSim(this).schedule(31*86400);
		Sim.start();
	}
	
	class EndOfSim extends Event {
		ReplayMonth day;

		public EndOfSim(ReplayMonth day) {
			this.day = day;
		}
		@Override
		public void actions() {
			try{
				writeDataInFile("final_dataset.csv",custServed);
				ReplayAllMonth.nextMonth();
			}
			catch (IOException e){
				e.printStackTrace();
			}
			Sim.stop();
			System.out.println("Sim stop");
		}
	}
	

}
