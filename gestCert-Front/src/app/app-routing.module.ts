import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './web-pages/login/login.component';
import {HomeComponent} from './web-pages/home/home.component';
import {CertificateComponent} from './web-pages/certificate/certificate.component';
import {ContactComponent} from './header-footer/contact/contact.component';
import {SiteComponent} from './header-footer/site/site.component';
import {AccessibiliteComponent} from './header-footer/accessibilite/accessibilite.component';
import {ProfileComponent} from './web-pages/profile/profile.component';
import {AuthenticationComponent} from './web-pages/authentication/authentication.component';
import {DemandComponent} from './web-pages/demand/demand.component';
import {ManagementComponent} from './web-pages/administration/management/management.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'authe', component: AuthenticationComponent },
  { path: 'accueil/:id', component: HomeComponent },
  { path: 'gestion', component: ManagementComponent },
  { path: 'gestion/uti', component: ManagementComponent },
  { path: 'gestion/pro', component: ManagementComponent },
  { path: 'gestion/pro/:id', component: ManagementComponent },
  { path: 'gestion/pro/ajout', component: ManagementComponent },
  { path: 'gestion/sce', component: ManagementComponent },
  { path: 'gestion/sce/:id', component: ManagementComponent },
  { path: 'gestion/sce/ajout', component: ManagementComponent },
  { path: 'gestion/app', component: ManagementComponent },
  { path: 'gestion/cer', component: ManagementComponent },
  { path: 'gestion/env', component: ManagementComponent },
  { path: 'gestion/pla', component: ManagementComponent },
  { path: 'gestion/rac', component: ManagementComponent },
  { path: 'gestion/sur', component: ManagementComponent },
  { path: 'certificat/:id', component: CertificateComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'site', component: SiteComponent },
  { path: 'accessibilite', component: AccessibiliteComponent },
  { path: 'profil/:id', component: ProfileComponent },
  { path: 'profil/ajout', component: ProfileComponent },
  { path: 'accueil/:id1/demande/:id2', component: DemandComponent },
  { path: '**', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
