import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Class master Cinema */
class Cinema 
{ 
	public static String  errorCheck;

	//Check if number of place > 0 and <= 9
	public static  boolean checkNumPlace( int numPlace ){
		boolean checkResult;
		if(numPlace >0 && numPlace <= 9){
			checkResult = true;
		}else{
			errorCheck = "Erreur de rentrée du nombre de place, il doit etre entre 1 et 9";
			checkResult = false;
		}
		return checkResult;
	}
	
	//Check if number of range >= 0 and <= 8
	public static  boolean checkNumRange( int numRange ){
		boolean checkResult;
		if(numRange >=0 && numRange <= 8){
			checkResult = true;
		}else{
			errorCheck = "Erreur de rentrée du nombre de rangée, il doit etre entre 1 et 8";
			checkResult = false;
		}
		return checkResult;
	}	
	
	//Show place free(_) or no (X)
	public static void showSalle( int[][] tab ){
		String Line = "";
		String LinePlace = "";
		System.out.println("");
		//For each range (i)
		for (int i = 0;i < tab.length; i++) {
			
			Line = (i+1) + " ";
			LinePlace = "  ";
			//For each place (j)
			for (int j = 0;j < tab[i].length;j++) {
				Line += "("+( tab[i][j]==0 ? "_" : "X" )+")";
				LinePlace += " " + (j+1) + " ";				
			}
			
			System.out.println(Line);
		}
		System.out.println(LinePlace);
	}
	
	
	public static void addSalle( int numRange, int numPlace, int[][] tab ){
		
		//For each range (i)
		for (int i = 0;i < tab.length; i++) {
			//For each place (j)

				//If good pos for range
				if( i == numRange ){
					//Check start with 0
					int startPlace = -1;
					
					//Check for range = numRange, the first pos free in startPlace
					for (int start = 0;start < tab[i].length;start++) {
						if( tab[i][start] == 0 && startPlace == -1){
							//Define first place free
							startPlace = start;
						}
						
					}

					//Check if range can add numPlace define, in start by startPlace
					int lastPosDefine = numPlace + startPlace ;
					if( (tab[i].length - startPlace) - (numPlace-1) > 0 ){
						//numPlace can add in range numRange
						for (int posDefine = startPlace;posDefine < lastPosDefine;posDefine++) {
							tab[numRange][posDefine] = 1;
						}
						
						
					}else{
						System.out.println("Le nombre de place demande n'est plus disponible dans cette rangée");
				
					}
				}

		}
		
	}
	// main(). 
	// Execute by default 
	public static void main(String args[]) {
		try{
			int[][] tableau;
			String resultLoop;
			tableau = new int[8][9];
			
			 BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));


			do {
				
				System.out.println("A quelle rangée voulez vous aller (1 - 8):"); 
				 int numRange = Integer.parseInt(reader.readLine())-1;
				if( checkNumRange(numRange) == false ){
					System.out.println(errorCheck);
					System.exit(1);
				}
				
				System.out.println("Nombre de places (1 - 9):"); 
				 int numPlace = Integer.parseInt(reader.readLine());
				
				if( checkNumPlace(numPlace) == false ){
					System.out.println(errorCheck);
					System.exit(1);
				}			
				
				addSalle( numRange, numPlace, tableau );
				showSalle(tableau);
				boolean repeat;
				//Loop for choose o or n, for command again
				do {
					System.out.println("Voulez vous commander de nouveau ? (o or n)"); 
					resultLoop = reader.readLine();
					
					if(!(resultLoop.equals("o") || resultLoop.equals("n") )){
						System.out.println("Error vous devez rentrez o ou n");
					}
				} while ( !(resultLoop.equals("o") || resultLoop.equals("n") ));
			} while (resultLoop.equals("o"));
        }
        catch(Exception e){
            System.out.println( "Error in application : "+ e.getMessage() );
			System.out.println(e.toString());
        }
		
	} 

}
