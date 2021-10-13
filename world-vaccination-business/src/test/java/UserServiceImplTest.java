import com.alisonjs.business.domain.User;
import com.alisonjs.business.repository.UserRepository;
import com.alisonjs.business.service.UserService;
import com.alisonjs.business.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Alison Sousa (alison.sousa@ufrn.br)
 * @since 13/10/2021
 */
public class UserServiceImplTest {


    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
    }

    @Nested
    class curdUser{

        @Test
        void save(){
            var user = getUserInstance();
            userService.save(user);
            Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
        }

        @Test
        void getAll(){
            userService.getAll();
            Mockito.verify(userRepository, Mockito.times(1)).getAll();
        }

        @Test
        void getOne(){
            Mockito.lenient().when(userRepository.getOne(ArgumentMatchers.anyLong())).thenReturn(getUserInstance());
            userService.getOne(1L);
            Mockito.verify(userRepository, Mockito.times(1)).getOne(ArgumentMatchers.anyLong());
        }

        @Test
        void getByUsername(){
            Mockito.lenient().when(userRepository.getByUsername(ArgumentMatchers.anyString())).thenReturn(getUserInstance());
            userService.getByUsername("user");
            Mockito.verify(userRepository, Mockito.times(1)).getByUsername(ArgumentMatchers.anyString());
        }

        @Test
        void login(){
            Mockito.lenient().when(userRepository.getByUsernameAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(getUserInstance());
            userService.login("user", "pass");
            Mockito.verify(userRepository, Mockito.times(1)).getByUsernameAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        }
    }


    public User getUserInstance(){
        return User.builder()
                .username("user")
                .password("pass")
                .email("email")
                .build();
    }
}
