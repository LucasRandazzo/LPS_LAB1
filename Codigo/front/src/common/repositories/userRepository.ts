import { AxiosResponse } from "axios";
import { User, UserLogin } from "../helpers/types";
import BaseRepository from "./BaseRepository";

class UserRepository extends BaseRepository {
    constructor() {
        super({controller: 'user'})
    }

    async login(user : UserLogin): Promise<User> {
        console.log(this.url)
        const response: AxiosResponse<User> = await this.post(user, "login")
        return response.data
    }

}

export default UserRepository;
