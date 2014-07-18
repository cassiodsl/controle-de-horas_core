package br.com.controledehoras.core.saldo;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import br.com.controledehoras.core.beans.Feriado;
import br.com.controledehoras.core.beans.RegistroArquivoBean;
import br.com.controledehoras.core.beans.Tempo;
import br.com.controledehoras.core.tempo.CalcTempoUtil;

/**
 * 
 * @author Cassio Lemos
 *
 */
public class CalculadoraMediaHelper {

	private CalcMedia calcMedia;
	private CalcTempoUtil calcTempoUtil;

	public CalculadoraMediaHelper(CalcMedia calcMedia,
			CalcTempoUtil calcTempoUtil) {
		this.calcMedia = calcMedia;
		this.calcTempoUtil = calcTempoUtil;
	}

	public Tempo calcularMediaDiariaParaEliminarSaldo(List<RegistroArquivoBean> registros,
			Quadrimestre quadrimetre, List<Feriado> feriados, int dataConsumirAte) {

		Map<String, RegistroArquivoBean> diasAgrupados = this.calcTempoUtil
				.agruparRegistrosPorDia(registros);

		Calendar inicioQuadrimestre = quadrimetre.getCalendarDataInicial();

		Tempo saldoDias = this.calcMedia.getSaldoTotalCalculadoPorDia(
				inicioQuadrimestre, diasAgrupados, feriados);

		System.out.println("Saldo final: " + saldoDias.getHorasString());

		Calendar consumirAte = this.calcTempoUtil.getCalendar(dataConsumirAte);

		Tempo divisao = this.calcMedia.getHorasNecessariasParaOPeriodo(
				consumirAte, saldoDias, feriados);

		System.out.println("Realizar at� " + dataConsumirAte + " - "
				+ divisao.getHorasString());
		return divisao;
	}

}
