import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {Certificate} from '../../model/certificate';
import {Application} from '../../model/application';
import {Environment} from '../../model/environment';
import {Root} from '../../model/root';
import {ProfileDataService} from '../../service/profile-data.service';
import {UserDataService} from '../../service/user-data.service';
import {CertificateDataService} from '../../service/certificate-data.service';
import {ApplicationDataService} from '../../service/application-data.service';
import {EnvironmentDataService} from '../../service/environment-data.service';
import {RootDataService} from '../../service/root-data.service';
import {AppUser} from '../../model/appUser';
import {FormBuilder, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-accueil',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  dateNow: Date = new Date();
  alertDate: Date = new Date();

  usersList: BehaviorSubject<AppUser[]>;
  idRH: string;
  editedUser: AppUser;

  certificatesList: BehaviorSubject<Certificate[]>;
  editedCertificate: Certificate[];
  applicationsList: BehaviorSubject<Application[]>;
  editedApplication: Application[];
  environmentsList: BehaviorSubject<Environment[]>;
  editedEnvironment: Environment[];
  rootsList: BehaviorSubject<Root[]>;
  editedRoot: Root[];

  /* TEST PRIMENG */
  certificate: Certificate;
//  certificate: Certificate = new PrimeCertificate();
  listCertificates: Certificate[];
  cols1: any[];
  cols2: any[];
  cols3: any[];
  selectedCertificate: Certificate;
  listApplications: Application[];

  loginForm = this.fb.group({
    applicationCCX: [null, Validators.compose([Validators.minLength(3), Validators.maxLength(3)])],
    applicationName: [null, Validators.compose([])],
    environmentName: [null, Validators.compose([])],
 //   applicationCCX: [null, Validators.compose([Validators.minLength(3), Validators.maxLength(3)])],
  });


  constructor(private fb: FormBuilder,
              private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private certificateDataService: CertificateDataService,
              private applicationDataService: ApplicationDataService,
              private environmentDataService: EnvironmentDataService,
              private rootDataService: RootDataService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.usersList = this.userDataService.availableUsers$;

    this.idRH = this.route.snapshot.params.id1;
 //   console.log('idUser : ' + this.idRH);

    this.userDataService.findUserByIdRH(this.idRH).subscribe(user => {
      this.editedUser = user;
  //    console.log('j\'ai trouvé le user !!!');
    });

    this.certificateDataService.availableCertificates$.subscribe(certificates => this.listCertificates = certificates);
    this.certificateDataService.getCertificatePrimeNg().then(certificates => {
      this.listCertificates = certificates;
      this.listCertificates.forEach(certificate => certificate.applicationCCX = certificate.application.codeCCX);
      this.listCertificates.forEach(certificate => certificate.applicationName = certificate.application.nameApplication);
      this.listCertificates.forEach(certificate => certificate.environmentName = certificate.environment.nameEnvironment);
      this.listCertificates.forEach(certificate => certificate.rootName = certificate.root.nameRoot);
    });

/*   this.serversList = this.serverDataService.availableServers$;
    this.serversList.subscribe(
      servers => this.listServers = servers
    );
    this.listServers = this.editedCertificate.servers;*/

 //   this.certificatesList = this.certificateDataService.availableCertificates$;
 //   this.getCertificate();

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.applicationsList.subscribe(
      applications => this.listApplications = applications
    );
    this.listApplications = this.editedUser.applications;
    console.log('appli user : ', this.listApplications);
 /*   this.applicationDataService.getApplicationPrimeNg().then( applications => {
      this.listApplications = applications;
      console.log('liste appli : ', applications);
      this.listApplications.forEach( application =>
        application.certificates.forEach(certificate =>
          certificate.applicationCCX = certificate.application.codeCCX));
      this.listApplications.forEach( application =>
        application.certificates.forEach(certificate =>
          certificate.applicationName = certificate.application.nameApplication));
      this.listApplications.forEach( application =>
        application.certificates.forEach(certificate =>
          certificate.environmentName = certificate.environment.nameEnvironment));
      this.listApplications.forEach( application =>
        application.certificates.forEach(certificate =>
          certificate.rootName = certificate.root.nameRoot));
    })*/
    this.getApplication();

    this.environmentsList = this.environmentDataService.availableEnvironments$;
    this.getEnvironment();

    this.rootsList = this.rootDataService.availableRoots$;
    this.getRoot();

    this.dateAlert();

    this.cols1 = [
      { field: 'applicationCCX', header: 'CCX', width: '80px' },
      { field: 'applicationName', header: 'Application' },
      { field: 'environmentName', header: 'Environnement' },
      { field: 'rootName', header: 'Type' },
      { field: 'dateIssue', header: 'Emission', width: '120px' }
    ];

    this.cols2 = [
      { field: 'applicationCCX', header: 'CCX', width: '80px' },
      { field: 'applicationName', header: 'Application' },
      { field: 'environmentName', header: 'Environnement' },
      { field: 'rootName', header: 'Type' },
    ];

    this.cols3 = [
      { field: 'applicationCCX', header: 'CCX', width: '80px' },
      { field: 'applicationName', header: 'Application' },
      { field: 'environmentName', header: 'Environnement' },
    ];
  }

  reset(logForm: NgForm): void {
    logForm.reset();
    this.router.navigate(['/accueil/' + this.editedUser.idRHUser]);
  }

 /* getCertificate(): void {
    this.certificateDataService.getCertificate().subscribe(certificates => this.editedCertificate = certificates);
  }*/

  getApplication(): void {
    this.applicationDataService.getApplication().subscribe(applications => this.editedApplication = applications);
  }

  getEnvironment(): void {
    this.environmentDataService.getEnvironment().subscribe(environments => this.editedEnvironment = environments);
  }

  getRoot(): void {
    this.rootDataService.getRoot().subscribe(roots => this.editedRoot = roots);
  }

  dateAlert(): void {
    const thisMonth = this.dateNow.getMonth();
    const oneMonth = 1;
    this.alertDate.setUTCMonth(thisMonth + oneMonth);
  }

  deconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      sessionStorage.clear();
      this.router.navigate(['']);
    }
  }

  onScroll() {
    window.scrollTo(0, 0);
  }
}
