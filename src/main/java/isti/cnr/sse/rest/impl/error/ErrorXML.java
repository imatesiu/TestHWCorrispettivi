package isti.cnr.sse.rest.impl.error;

import java.util.HashMap;
import java.util.Map;

public enum ErrorXML {
	XML_non_conforme_al_tracciato (0),
	La_CSR_non_e_in_formato_DER_o_PEM (1),
	Il_formato_della_matricola_non_e_corretto (2),
	La_lunghezza_della_chiave_del_certificato_non_rispetta_i_requisiti_minimi (3),
	Dispositivo_gia_censito (4),
	Dispositivo_MarchioFabbricante_e_obbligatorio_se_ (5),
	Dispositivo_Modello_e_obbligatorio_e_obbligatorio_se_ (6),
	Dispositivo_RifApprovazioneDispositivo_e_obbligatorio_se (7),
	Dispositivo_MarchioFabbricante_non_e_valido (8),
	Dispositivo_Modello_non_e_valido (9),
	Dispositivo_RifApprovazioneDispositivo_non_e_valido (10),
	TecnicoVerificatore_CodiceFiscale_non_e_valido (11),
	TecnicoVerificatore_PIvaSocieta_IdPaese_non_e_valido (12),
	TecnicoVerificatore_PivaSocieta_IdCodice_non_e_valido (13),
	Dispositivo_in_stato_non_coerente_con_la_richiesta (14),
	Matricola_oltre_i_30_caratteri_troncata (15),
	Errore_generico_nella_generazione_del_certificato_dispositivo (16),
	XML_con_Firma_non_integra (99999),
	Dispositivo_non_attivabile_si_puo_attivare_solo_un_dispositivo_nuovo_o_DISATTIVATO (1),
	Gestore_non_censito (11),
	InformazioniAddizionali_RT_MatricolaRegistratoreDiCassa_e_obbligatorio_per_lattivazione_di_un_modulo_di_invio (109),
	InformazioniAddizionali_RT_DataMessaInServizio_non_puo_essere_nel_passato (110),
	TecnicoVerificatore_CodiceFiscale_non_e_valido2 (111),
	TecnicoVerificatore_PivaSocieta_IdPaese_non_e_valido2 (112),
	TecnicoVerificatore_PivaSocieta_IdCodice_non_e_valido2 (113),
	Partita_Iva_indicata_non_valida (114),
	Soggetto_gia_accreditato_con_altra_partita_iva (115),
	Dispositivo_non_ATTIVATO (200),
	Trasmissione_Dispositivo_Tipo_non_coerente_con_il_certificato (201),
	Date_non_coerenti_sulPeriodoInattivo (202),
	Data_nel_futuro_sulDataOraRilevazione (203),
	Data_nel_futuro_sulInterventoTecnico_DataOra (204),
	Data_nel_futuro_sulSegnalazioni_DataOra (206),
	Incongruenza_Tipo_Dispositivo_e_Sezione_Dati_contabili (207),
	Dati_intervento_tecnico_non_congruenti (208),
	Dati_segnalazioni_non_congruenti (209),
	Matricola_del_dispositivo_DA_non_valida_o_non_appartenete_al_gestore (210),
	Dispositivo_DA_indicato_non_ATTIVATO (211),
	Trasmissione_Dispositivo_e_obbligatorio (212),
	Formato_geolocalizzazione_non_in_gradi_decimali (213),
	Data_nel_futuro_sullelemento_DataOra_prelievo_precedente (214),
	Dispositivo_in_stato_non_coerente_per_la_segnalazione_di_un_evento (700),
	Codice_ed_evento_incoerenti (701),
	Data_evento_nel_futuro (705),
	Descrizione_obbligatoria_per_il_codice_dettaglio (706),
	Incoerenza_fra_codice_dettaglio_evento_e_tipologia_dispositivo (707),
	Data_assente_o_formalmente_errata (708),
	Dati_di_dettaglio_assenti_o_incompleti (709),
	Evento_richiesto_inesistente (710),
	Codice_dettaglio_richiesto_inesistente (711),
	Evento_non_ammesso_per_un_dispositivo_Fuori_ServizioIl_dispositivo_ritornera_in_servizio_alla_prima_trasmissione (712);

	private final int value;

	ErrorXML(int i){
		this.value = i;
	}
	
	

	public int getValue() {
		return value;
	}
	//****** Reverse Lookup Implementation************//
	 
    //Lookup table
    private static final Map<String,ErrorXML> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
    	for(ErrorXML env : ErrorXML.values()) {
    		lookup.put(env.name(), env);
    	}
    	
    }
    //This method can be used for reverse lookup purpose
    public static ErrorXML get(String url) 
    {
        return lookup.get(url);
    }
	
}
