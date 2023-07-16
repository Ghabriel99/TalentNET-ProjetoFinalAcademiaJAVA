import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HomeComponent } from './pages/home/home.component';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { authInterceptorProviders } from './services/auth.interceptor';
import { DashbordComponent } from './pages/admin/dashbord/dashbord.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { SidebarComponent } from './pages/admin/sidebar/sidebar.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { AddCandidatoComponent } from './pages/admin/add-candidato/add-candidato.component';
import { ViewCandidatoComponent } from './pages/admin/view-candidato/view-candidato.component';
import { AddVagaComponent } from './pages/admin/add-vaga/add-vaga.component';
import { AtualizarCandidatoComponent } from './pages/admin/atualizar-candidato/atualizar-candidato.component';
import { ViewVagaComponent } from './pages/admin/view-vaga/view-vaga.component';
import { AtualizarVagaComponent } from './pages/admin/atualizar-vaga/atualizar-vaga.component';
import { FooterComponent } from './pages/admin/footer/footer.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    DashbordComponent,
    UserDashboardComponent,
    PerfilComponent,
    SidebarComponent,
    WelcomeComponent,
    AddCandidatoComponent,
    ViewCandidatoComponent,
    AddVagaComponent,
    AtualizarCandidatoComponent,
    ViewVagaComponent,
    AtualizarVagaComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    ReactiveFormsModule

  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
