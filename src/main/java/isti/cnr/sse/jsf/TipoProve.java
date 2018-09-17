package isti.cnr.sse.jsf;

import javax.faces.bean.ManagedBean;

/**
 * Created by m4rt3 on 20/11/2016.
 */

public enum TipoProve {

    Termiche (1),
    Impermeabilita (2),
    Vibrazione (3),
    ImmunitaScaricheElettrostaticheESD (4),
    ImmunitaCampoElettromagneticoRadiofrequenza (5),
    ImmunitaTransitoriVelociEFTBURST_PortaComunicazione (6),
    ImmunitaImpulsiSURGE_PortaComunicazione (7),
    ImmunitaDisturbiCondottiContinuiRadiofrequenza_PortaComunicazione (8),
    ImmunitaCampiMagneticiFrequenzaRete (9),
    ImmunitaBuchiInterruzioniTensione(10),
    VariazioniTensione (11),
    ProvaDurataBatteria (12),

    BatteriaSottoProtezioneSF (13),
    AlimentazioneBatteriaSenzaVincoloFiscale (14),

	Guastoemalfunzionamento (15),
	NonSelezionata (16),
	ImmunitaImpulsiSURGE_Alimentazione (17),
	ImmunitaDisturbiCondottiContinuiRadiofrequenza_Alimentazione (18),
	ImmunitaTransitoriVelociEFTBURST_Alimentazione (19);

    TipoProve(int i) {
    }

    public static TipoProve get(String tipo) {
        if (tipo.equals("Termiche")) {
            return Termiche;
        }
        if (tipo.equals("Impermeabilita'")) {
            return Impermeabilita;
        }
        if (tipo.equals("Vibrazione")) {
            return Vibrazione;
        }
        if (tipo.equals("ImmunitaScaricheElettrostaticheESD'")) {
            return ImmunitaScaricheElettrostaticheESD;
        }
        if (tipo.equals("ImmunitaCampoElettromagneticoRadiofrequenza")) {
            return ImmunitaCampoElettromagneticoRadiofrequenza;
        }
        if (tipo.equals("ImmunitaTransitoriVelociEFTBURST_PortaComunicazione")) {
            return ImmunitaTransitoriVelociEFTBURST_PortaComunicazione;
        }
        if (tipo.equals("ImmunitaImpulsiSURGE_PortaComunicazione")) {
            return ImmunitaImpulsiSURGE_PortaComunicazione;
        }
        
        if (tipo.equals("ImmunitaDisturbiCondottiContinuiRadiofrequenza_PortaComunicazione")) {
            return ImmunitaDisturbiCondottiContinuiRadiofrequenza_PortaComunicazione;
        }
        if (tipo.equals("ImmunitaTransitoriVelociEFTBURST_Alimentazione")) {
            return ImmunitaTransitoriVelociEFTBURST_Alimentazione;
        }
        if (tipo.equals("ImmunitaImpulsiSURGE_Alimentazione")) {
            return ImmunitaImpulsiSURGE_Alimentazione;
        }
        
        if (tipo.equals("ImmunitaDisturbiCondottiContinuiRadiofrequenza_Alimentazione")) {
            return ImmunitaDisturbiCondottiContinuiRadiofrequenza_Alimentazione;
        }
        if (tipo.equals("ImmunitaCampiMagneticiFrequenzaRete")) {
            return ImmunitaCampiMagneticiFrequenzaRete;
        }
        if (tipo.equals("ImmunitaBuchiInterruzioniTensione'")) {
            return ImmunitaBuchiInterruzioniTensione;
        }
        if (tipo.equals("VariazioniTensione")) {
            return VariazioniTensione;
        }
        if (tipo.equals("ProvaDurataBatteria")) {
            return ProvaDurataBatteria;
        }
        if (tipo.equals("Batteria Sotto Protezione SF")) {
            return BatteriaSottoProtezioneSF;
        }
        if (tipo.equals("Alimentazione Batteria Senza Vincolo Fiscale")) {
            return AlimentazioneBatteriaSenzaVincoloFiscale;
        }
        return NonSelezionata;
    }
}
