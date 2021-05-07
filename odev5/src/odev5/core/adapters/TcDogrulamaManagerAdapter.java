package odev5.core.adapters;

import odev5.nviService.Nvi;

public class TcDogrulamaManagerAdapter implements TcDogrulamaService {

	@Override
	public boolean dogrula(String tcNo) {
		Nvi nvi = new Nvi();
		return nvi.tcDogrula(tcNo);
	}

}
