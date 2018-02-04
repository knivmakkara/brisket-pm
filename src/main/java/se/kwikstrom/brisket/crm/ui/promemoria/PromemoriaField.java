package se.kwikstrom.brisket.crm.ui.promemoria;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.CallbackDataProvider.CountCallback;
import com.vaadin.data.provider.CallbackDataProvider.FetchCallback;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.crm.domain.Customer;
import se.kwikstrom.brisket.crm.domain.Promemoria;
import se.kwikstrom.brisket.crm.repository.CustomerRepository;
import se.kwikstrom.brisket.crm.ui.AddressField;

public class PromemoriaField extends CustomField<Promemoria> {

	private DateTimeField due = new DateTimeField("Datum/Tid");
	private ComboBox<Customer> customer = new ComboBox<>("Kund");
	private TextField customerPhone = new TextField("Telefon");
	private TextField customerEmail = new TextField("E-Post");
	private TextField menu = new TextField("Meny");
	private TextField allergies = new TextField("Allergier");
	private TextField receivedBy = new TextField("Mottagen av");
	private TextField deliveryType = new TextField("Leveranssätt");
	private TextField staff = new TextField("Personal på plats");
	private TextField rental = new TextField("Hyrgods");
	private TextField misc = new TextField("Övrigt");
	private AddressField invoiceTo = new AddressField();
	private AddressField deliverTo = new AddressField();

	private Binder<Promemoria> binder = new BeanValidationBinder<>(Promemoria.class);

	private CustomerRepository customerRepository;

	public PromemoriaField(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Promemoria getValue() {
		return binder.getBean();
	}

	@Override
	protected Component initContent() {

		FetchCallback<Customer, String> fetchCallback = query -> {
			if (query.getFilter().isPresent()) {
				return customerRepository.findAllByNameLike(query.getFilter().get(), query.getOffset(), query.getLimit())
				    .stream();
			} else {
				return customerRepository.findAll(query.getOffset(), query.getLimit()).stream();
			}
		};
		CountCallback<Customer, String> countCallback = query -> {
			int count = (int) (query.getFilter().isPresent()
			    ? customerRepository.countByNameLikeIgnoreCase(query.getFilter().get())
			    : customerRepository.count());
			System.out.println(String.format("Returning count: %s", count));
			return count;
		};
		DataProvider<Customer, String> dataProvider = DataProvider.fromFilteringCallbacks(fetchCallback, countCallback);
		customer.setDataProvider(dataProvider);
		customer.setItemCaptionGenerator((c) -> c.getName());

		binder.bindInstanceFields(this);
		return new VerticalLayout(due, customer, customerPhone, customerEmail, menu, allergies, receivedBy);
	}

	@Override
	protected void doSetValue(Promemoria value) {
		binder.setBean(value);
	}

}
