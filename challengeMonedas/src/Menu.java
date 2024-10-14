import java.util.Scanner;

public class Menu {

    private Conversor conversor;
    private Scanner scanner;

    public Menu() {
        this.conversor = new Conversor();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int option;
        do {
            //imprimir menu
            System.out.println("***************************************");
            System.out.println("**   Sea bienvenido/a al Conversor de Moneda =] **");
            System.out.println("***************************************");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.println("***************************************");
            System.out.print("Elija una opción válida: ");
            option = scanner.nextInt();
            scanner.nextLine();

            String fromCurrency = "";
            String toCurrency = "";
            switch (option) {
                case 1:
                    fromCurrency = "USD";
                    toCurrency = "ARS";
                    break;
                case 2:
                    fromCurrency = "ARS";
                    toCurrency = "USD";
                    break;
                case 3:
                    fromCurrency = "USD";
                    toCurrency = "BRL";
                    break;
                case 4:
                    fromCurrency = "BRL";
                    toCurrency = "USD";
                    break;
                case 5:
                    fromCurrency = "USD";
                    toCurrency = "COP";
                    break;
                case 6:
                    fromCurrency = "COP";
                    toCurrency = "USD";
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción correcta.");
            }

            if (option >= 1 && option <= 6) {
                System.out.println("Introduce el monto que deseas convertir: ");
                double amount = scanner.nextDouble();

                // Realizar la solicitud a la API para obtener la tasa de cambio
                String jsonResponse = conversor.getConversionRate(fromCurrency, toCurrency);

                // Obtener la tasa de cambio del JSON
                double conversionRate = conversor.parseJsonResponse(jsonResponse);

                // Realizar la conversión y mostrar el resultado
                double convertedAmount = conversor.convertValues(amount, conversionRate);
                System.out.printf("El monto %.2f %s es equivalente a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
            }

        } while (option != 7);
    }
}
