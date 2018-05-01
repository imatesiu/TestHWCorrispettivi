package isti.cnr.sse.jsf;

import javax.faces.bean.ManagedBean;

/**
 * Created by m4rt3 on 20/11/2016.
 */

public enum TipoProve {

    Termiche (1),
    Impermeabilita (2),
    Vibrazione (3),
    DisturbiElettromagnetici (4),
    DisturbiCondotti (5),
    BatteriaSottoProtezioneSF (6),
    AlimentazioneBatteriaSenzaVincoloFiscale (7),
    ScaricheElettrostatiche (8),
	Guastoemalfunzionamento (9);

    TipoProve(int i) {
    }

    public static TipoProve get(String tipo) {
        if (tipo.equals("Termiche")) {
            return Termiche;
        }
        if (tipo.equals("DisturbiElettromagnetici")) {
            return DisturbiElettromagnetici;
        }
        if (tipo.equals("Disturbi Elettromagnetici")) {
            return DisturbiElettromagnetici;
        }
        if (tipo.equals("Impermeabilita'")) {
            return Impermeabilita;
        }
        if (tipo.equals("Vibrazione")) {
            return Vibrazione;
        }
        if (tipo.equals("Disturbi Condotti")) {
            return DisturbiCondotti;
        }
        if (tipo.equals("Batteria Sotto Protezione SF")) {
            return BatteriaSottoProtezioneSF;
        }
        if (tipo.equals("Alimentazione Batteria Senza Vincolo Fiscale")) {
            return AlimentazioneBatteriaSenzaVincoloFiscale;
        }
        return ScaricheElettrostatiche;
    }
}
