import { User, UserLogin } from "../helpers/types";
import UserRepository from "../repositories/userRepository";

class UserService {
  userRepository: UserRepository;

  constructor() {
    this.userRepository = new UserRepository()
  }

  async login(user : UserLogin): Promise<User> {
    return this.userRepository.login(user)
  }
}

const userService = new UserService();

export default userService;
