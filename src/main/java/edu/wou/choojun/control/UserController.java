package edu.wou.choojun.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wou.choojun.entity.User;
import edu.wou.choojun.model.UserRepository;
import edu.wou.choojun.service.UserService;

@Controller
public class UserController
{
	private UserService userService = new UserService();
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@GetMapping("/index")
	public String showUserList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size)
	{
		model.addAttribute("users", userRepository.findAll());
		
		final int currentPage = page.orElse(1);
		final int pageSize = size.orElse(5);
		
		Iterable<User> ite = userRepository.findAll();
		Iterator<User> it = ite.iterator();
		List<User> itData = new ArrayList<User>();
		while (it.hasNext())
			itData.add(it.next());
		
		Page<User> userPage = userService.findPaginated(currentPage - 1, pageSize, itData);

		long totalItems = userPage.getTotalElements();
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("userPage", userPage);

		int totalPages = userPage.getTotalPages();
		model.addAttribute("totalPages", totalPages);

		if (totalPages > 0)
		{
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		return "index";
	}

	@GetMapping("/signup")
	public String showSignUpForm(User user)
	{
		return "add-user";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			return "add-user";
		}

		userRepository.save(user);
		return "redirect:/index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model)
	{
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);

		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			user.setId(id);
			return "update-user";
		}

		userRepository.save(user);

		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model)
	{
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);

		return "redirect:/index";
	}
}
