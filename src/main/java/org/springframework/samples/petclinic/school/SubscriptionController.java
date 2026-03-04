package org.springframework.samples.petclinic.school;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Collection;


@Controller
public class SubscriptionController {


	private final SubscriptionRepository subscriptionRepository;


	public SubscriptionController(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}


	@GetMapping("/subscriptions")
	public String showPricingTable(Model model) {
		Collection<Subscription> subscriptions = subscriptionRepository.findAll();
		model.addAttribute("plans", subscriptions.stream().toList());
		return "subscriptions/pricing";
	}


}
