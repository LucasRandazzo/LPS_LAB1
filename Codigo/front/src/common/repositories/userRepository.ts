import { AxiosResponse } from "axios";
import { User, UserLogin, UserToken } from "../helpers/types";
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

    async register(user : User): Promise<User> {
        console.log(this.url)
        const response: AxiosResponse<User> = await this.post(user, "register")
        return response.data
    }

    async recoverpassword(email : string): Promise<any> {
        return await this.post(email, "register")
    }

    async checktoken(userToken: UserToken): Promise<boolean> {
        const response: AxiosResponse<boolean> = await this.post(userToken, "checktoken");
        return response.data;
    }

    async changepassword(userLogin: UserLogin) : Promise<void> {
        await this.post(userLogin, "changepassword")
    }

}

export default UserRepository;
