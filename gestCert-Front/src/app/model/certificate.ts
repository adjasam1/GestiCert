import {Application} from './application';
import {Environment} from './environment';
import {Plateform} from './plateform';
import {Root} from './root';
import {Server} from './server';
import {AddressAlternative} from './addressAlternative';

export class Certificate {

  constructor(public idCertificate: number,
              public nameCertificate: string,
              public linkAddressPrincipal: string,
              public linkInstallation: string,
              public passwordCertificate: string,
              public dateIssue: Date,
              public dateEndValidity: Date,
              public application: Application,
              public environment: Environment,
              public plateform: Plateform,
              public root: Root,
              public server: Server[],
              public addressAlternative: AddressAlternative[]) {}

}
