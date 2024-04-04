import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    standalone: true,
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule
    ]
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    hasSubmitted: boolean = false;

    get username() { return this.loginForm.get('username'); }
    get password() { return this.loginForm.get('password'); }

    constructor(private fb: FormBuilder, private router: Router, private loginService : LoginService) {
        this.loginForm = this.fb.group({
            username: ['test@gmail.com', [Validators.required]],
            password: ['test', [Validators.required]]
        });
    }
    ngOnInit(): void { }

    onLogout() {
        this.loginService.logout().subscribe({
            next(value) {

            },
        })
    }

    onSubmit() {
        this.hasSubmitted = true;

        if (this.loginForm.valid) {
            console.log(this.username?.value)

            this.loginService.login(this.username?.value, this.password?.value).subscribe({
                next: (value) => {
                    if (value !== null) {
                        this.router.navigate(['commande'])
                    }
                },
            })

            this.loginForm.reset();
            this.hasSubmitted = false;
        }
        else {
        }
    }
}