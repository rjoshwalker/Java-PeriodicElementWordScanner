
public class ElementsList {
	
	protected String[] List = {//Holds the elements in order of their symbol
			"Ag Silver",
			"Al Aluminum",
			"Am Americium",
			"Ar Argon",
			"As Arsenic",
			"At Astatine",
			"Au Gold",
			"B Boron",
			"Ba Barium",
			"Be Beryllium",
			"Bh Bohrium",
			"Bi Bismuth",
			"Bk Berkelium",
			"Br Bromine",
			"C Carbon",
			"Ca Calcium",
			"Cd Cadmium",
			"Ce Cerium",
			"Cf Californium",
			"Cl Chlorine",
			"Cm Curium",
			"Cn Copernicium",
			"Co Cobalt",
			"Cr Chromium",
			"Cs Cesium",
			"Cu Copper",
			"Db Dubnium",
			"Ds Darmstadtium",
			"Dy Dysprosium",
			"Er Erbium",
			"Es Einsteinium",
			"Eu Europium",
			"F Fluorine",
			"Fe Iron",
			"Fl Flerovium",
			"Fm Fermium",
			"Fr Francium",
			"Ga Gallium",
			"Gd Gadolinium",
			"Ge Germanium",
			"H Hydrogen",
			"He Helium",
			"Hf Hafnium",
			"Hg Mercury",
			"Ho Holmium",
			"Hs Hassium",
			"I Iodine",
			"In Indium",
			"Ir Iridium",
			"K Potassium",
			"Kr Krypton",
			"La Lanthanum",
			"Li Lithium",
			"Lr Lawrencium",
			"Lu Lutetium",
			"Lv Livermorium",
			"Md Mendeleviu",
			"Mg Magnesium",
			"Mn Manganese",
			"Mo Molybdenum",
			"Mt Meitnerium",
			"N Nitrogen",
			"Na Sodium",
			"Nb Niobium",
			"Nd Neodymium",
			"Ne Neon",
			"Ni Nickel",
			"No Nobelium",
			"Np Neptunium",
			"O Oxygen",
			"Os Osmium",
			"P Phosphorus",
			"Pa Protactinium",
			"Pb Lead",
			"Pd Palladium",
			"Pm Promethium",
			"Po Polonium",
			"Pr Praseodymium",
			"Pt Platinum",
			"Pu Plutonium",
			"Ra Radium",
			"Rb Rubidium",
			"Re Rhenium",
			"Rf Rutherfordium",
			"Rg Roentgenium",
			"Rh Rhodium",
			"Rn Radon",
			"Ru Ruthenium",
			"S Sulfur",
			"Sb Antimony",
			"Sc Scandium",
			"Se Selenium",
			"Sg Seaborgium",
			"Si Silicon",
			"Sm Samarium",
			"Sn Tin",
			"Sr Strontium",
			"Ta Tantalum",
			"Tb Terbium",
			"Tc Technetium",
			"Te Tellurium",
			"Th Thorium",
			"Ti Titanium",
			"Tl Thallium",
			"Tm Thulium",
			"U Uranium",
			"Uuo Ununoctium",
			"Uup Ununpentium",
			"Uus Ununseptium",
			"Uut Ununtrium",
			"V Vanadium",
			"W Tungsten",
			"Xe Xenon",
			"Y Yttrium",
			"Yb Ytterbium",
			"Zn Zinc",
			"Zr Zirconium"
	};
	
	protected String[] SymList;//Will hold the symbols only.
	protected String[] EleList;//Will hold the elements only.
	
	public ElementsList() {
		SymList = new String[List.length];
		EleList = new String[List.length];
		splitEleSym();
	}
	
	protected void splitEleSym() {//Function to separate the list.
		String[] wordSplit;
		for(int i = 0;i < List.length;i++)
		{
			wordSplit = List[i].split("\\s+");
			
			SymList[i] = wordSplit[0];
			EleList[i] = wordSplit[1];
		}
	}
	
	public String[] getSymbols() {
		return SymList;
	}
	
	public String getSymbols(int x) {
		return SymList[x];
	}
	
	public String[] getElements() {
		return EleList;
	}
	
	public String[] getList() {
		return List;
	}
	
	public boolean isASym(String test) {
		String lowercase = test.toLowerCase();
		
		for(int i = 0;i < SymList.length;i++)
		{
			String lowerSym = SymList[i].toLowerCase();

			if(lowercase.equals(lowerSym))
			{
				return true;
			}
			else continue;
		}
		
		return false;
	}

}
