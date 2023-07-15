import java.sql.*;
import java.util.*;
class HealthManagement{
	public static void main(String arg[]){
		String url="jdbc:mysql://localhost:3306/rishi";
		System.out.println("Welcome to Health Profile Management");
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded");
			Connection con=DriverManager.getConnection(url,"root","Rishi@2002");
			System.out.println("connection established with database!");
			System.out.println("-----------------------------------------------------------------------------------");
			Scanner s=new Scanner(System.in);
			int inp;
			int id,age,k;
			String name,bg,bp,sg,ot;
			while(true){
				System.out.println("press 1->to enter details into database\npress 2->to retrive details");
				inp=s.nextInt();
				if(inp==1){
					while(true){
						System.out.println("enter every detail without space if u need division enter underscore(_)");
						System.out.println("---------*enter employee details*----------");
						System.out.println("enter id:");
						id=s.nextInt();
						System.out.println("enter name:");
						name=s.next();
						System.out.println("enter age:");
						age=s.nextInt();
						System.out.println("enter blood group:");
						bg=s.next();
						System.out.println("enter bp problem as high or low if employee suffers with bp problems else enter normal");
						bp=s.next();
						System.out.println("enter yes if employee have diabities problem else no");
						sg=s.next();
						System.out.println("enter list of any diseases if he suffers with placing a comma b/w every problem");
						ot=s.next();
						String q="insert into HealthDetails(id,name,age,BG,BP,diabities,other) values(?,?,?,?,?,?,?)";
						PreparedStatement ps=con.prepareStatement(q);
						ps.setInt(1,id);
						ps.setString(2,name);
						ps.setInt(3,age);
						ps.setString(4,bg);
						ps.setString(5,bp);
						ps.setString(6,sg);
						ps.setString(7,ot);
						ps.executeUpdate();
						System.out.println("--------*details succesfully stored!!  :)*--------");
						System.out.println("enter 1 to continue entering else 0");
						k=s.nextInt();
						if(k==0)
							break;
						else
							System.out.println("---------------------------------------------------------------------------------");
						}
				}
				else{
					while(true){
						System.out.println("enter\n1->to retrive all employees details \n2->to retrive a particular employee details\n3->to retrive a particular employee's particular column");
						k=s.nextInt();
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("select * from HealthDetails");
						if(k==1){
							System.out.println("-----------------------------------------------------------------");
							while(rs.next()){
								id=rs.getInt(1);
								name=rs.getString(2);
								age=rs.getInt(3);
								bg=rs.getString(4);
								bp=rs.getString(5);
								sg=rs.getString(6);
								ot=rs.getString(7);
								
								System.out.println("id:"+id+"\nname:"+name+"\nage:"+age);
								System.out.println("BloodGroup:"+bg+"\nBP:"+bp+"\nDiabities problem:"+sg+"\nother problems:"+ot);
								System.out.println("------------------------------------------------------------------------");
							}
						}
						else if(k==2){
							int e=0;
							System.out.println("enter id of employee whos info needed:");
							id=s.nextInt();
							while(rs.next()){
								if(rs.getInt(1)==id){
									System.out.println("-----------------------------------------------------------------");
									System.out.println("id:"+rs.getInt(1)+"\nname:"+rs.getString(2)+"\nage:"+rs.getInt(3));
								System.out.println("BloodGroup:"+rs.getString(4)+"\nBP:"+rs.getString(5)+"\nDiabities problem:"+rs.getString(6)+"\nany other problem:"+rs.getString(7));
								e=1;
								System.out.println("-----------------------------------------------------------------");
								break;}
							}
							if(e==0)
								System.out.println("not found!!");
						}
						else{
							int e=0;
							System.out.println("enter id of employee whos info needed:");
							id=s.nextInt();
							System.out.println("enter\n4->blood group \n5->bp\n6->diabities\n7->to check any other problems");
							k=s.nextInt();
							while(rs.next()){
								if(rs.getInt(1)==id){
								System.out.println("-----------------------------------------------------------------");
								System.out.println(rs.getString(k));
								System.out.println("-----------------------------------------------------------------");
								e=1;
								break;}
							}
							if(e==0)
								System.out.println("not found");
						}
					  System.out.println("if u need more details enter 1 else 0:");
					  k=s.nextInt();
					  if(k==0)
						  break;
					  else
						  System.out.println("--------------------------------------------------------------------------------------------------------------------");
					}
				}
				System.out.println("if you want to retrive or enter details again press 1 else 0");
				k=s.nextInt();
				if(k==0)
					break;
				System.out.println("____________________________________________________________________");
			}
		con.close();
		System.out.println("connection closed (:");
		System.out.println("----------*have a good day :)*---------");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}