package web.mutbrocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.User;
import web.mutbrocha.repository.ProdutoRepository;
import web.mutbrocha.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void salvar(User user) {
		userRepository.save(user);
	}
	
	@Transactional
	public void alterar(User user) {
		userRepository.save(user);
	}

	@Transactional
	public User alterarComRetorno(User user) {
		return userRepository.save(user);
	}
}
