package org.example.recipes.login;

import org.example.recipes.user.UserRepository;
import org.example.recipes.user.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class IdGeneratorService {
    private final UserRepository userRepo;
    private static final String PREFIX = "UUID";
    private static final int PAD_SIZE = 6;

    public IdGeneratorService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(readOnly = true)
    public synchronized String generateId() {
        Optional<Users> lastOpt = userRepo.findTopByOrderByIdDesc();
        int nextSeq = 1;
        if (lastOpt.isPresent()) {
            String lastId = lastOpt.get().getId();
            if (lastId.startsWith(PREFIX)) {
                String numPart = lastId.substring(PREFIX.length());
                try {
                    nextSeq = Integer.parseInt(numPart) + 1;
                } catch (NumberFormatException e) {
                    // nếu format lạ, vẫn để nextSeq = 1
                }
            }
        }
        // zero-pad đến PAD_SIZE chữ số
        String padded = String.format("%0" + PAD_SIZE + "d", nextSeq);
        return PREFIX + padded;
    }
}
