package se.kwikstrom.brisket.crm.ui.promemoria;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import se.kwikstrom.brisket.crm.domain.Promemoria;
import se.kwikstrom.brisket.crm.repository.PromemoriaRepository;

public class PromemoriaWindow extends Window {
	private PromemoriaField promemoriaField = new PromemoriaField();
	private PromemoriaRepository promemoriaRepository;
	private Runnable promemoriaSavedListener;

	public PromemoriaWindow(Promemoria pm, PromemoriaRepository promemoriaRepository) {
		super("PM");
		this.promemoriaRepository = promemoriaRepository;
		init(pm);
	}

	private void init(Promemoria pm) {
		this.promemoriaField.setValue(pm);
		this.center();
		this.setModal(true);
		this.setResizable(false);
		this.setContent(new VerticalLayout(promemoriaField));
	}

}
