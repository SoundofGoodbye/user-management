import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

// const usersUrl = serverUrl + '/users'
const headers = new HttpHeaders()
  .set("Content-Type", "application/json");

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient) {

    }

  serverUrl = 'http://localhost:8080/users';

  getUsers() {
    return this.http.get<User[]>(
      this.serverUrl, {observe: 'response'}
    );
  }

  updateUser(user: User): Observable<User> {
    var obs = this.http.patch<User>(this.serverUrl + "/" + user.id, user, {headers:headers});
    obs.subscribe();

    return obs;
  }

  deleteUser(id: number):void {
    this.http.delete(this.serverUrl + "/" + id).subscribe(() => console.log("user deleted"));
  }

  addUser(user: User): Observable<User> {
    var obs = this.http.post<User>(this.serverUrl, user, {headers:headers});
    obs.subscribe();

    return obs;
  }
}
