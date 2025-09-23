package Circulo;

public class ProgramaCirculo {
	
	public static void main(String[] args) {
		
		Circulo miCirculo = new Circulo();
		double area = miCirculo.getArea();
		System.out.println("area: " + area);
		miCirculo.setRadio(300);
		area = miCirculo.getArea();
		
		System.out.println("area: "+ area);
	}
	

	
	
	

}
