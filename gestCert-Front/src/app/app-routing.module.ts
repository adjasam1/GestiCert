import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './web-pages/login/login.component';
import {AuthenticationComponent} from './web-pages/authentication/authentication.component';
import {HomeComponent} from './web-pages/home/home.component';
import {CertificateComponent} from './web-pages/certificate/certificate.component';
import {ContactComponent} from './header-footer/contact/contact.component';
import {SiteComponent} from './header-footer/site/site.component';
import {AccessibiliteComponent} from './header-footer/accessibilite/accessibilite.component';
import {ProfileComponent} from './web-pages/profile/profile.component';
import {ManagementComponent} from './web-pages/management/management.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'authe', component: AuthenticationComponent },
  { path: 'accueil/:id', component: HomeComponent },
  { path: 'gestion', component: ManagementComponent },
  { path: 'certificat/:id', component: CertificateComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'site', component: SiteComponent },
  { path: 'accessibilite', component: AccessibiliteComponent },
  { path: 'profil/:id', component: ProfileComponent },
  { path: 'profil/ajout', component: ProfileComponent },
  { path: '**', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }