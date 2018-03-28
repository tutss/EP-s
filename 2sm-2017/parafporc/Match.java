import java.util.Arrays;
public class Match {

  public static void match(NutsAndBolts nutsAndBolts) {
    Nut[] porcas = nutsAndBolts.nuts();
    Bolt[] parafusos = nutsAndBolts.bolts();

    Arrays.sort(porcas);
    Arrays.sort(parafusos);
  }

}
