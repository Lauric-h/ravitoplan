package com.java.ravito_plan.user.domain.usecase.getByUsername;

import com.java.ravito_plan.user.domain.model.User;
import com.java.ravito_plan.user.domain.ports.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetByUsernameImpl implements GetByUsername {

    private final UserRepository userRepository;

    public GetByUsernameImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void execute(GetByUsernameRequest request, GetByUsernamePresenter presenter) {
        User user = this.userRepository.findByUsername(request.username());
        presenter.present(new GetByUsernameResponse(user));
    }
}
