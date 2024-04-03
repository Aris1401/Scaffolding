import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

const baseUrl = 'http://localhost:8080/api/v1/auth'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class LoginService {
    constructor(private http : HttpClient) {}

    login(loginName : string, loginPass : string) {
        let loginInfos = {
            loginName: loginName,
            loginPass: loginPass
        }

        return this.http.post(baseUrl + "/login", loginInfos, { withCredentials: true })
    }

    logout() {
        return this.http.post(baseUrl + '/logout', {}, {withCredentials: true});
    }
}
