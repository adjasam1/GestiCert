import {Certificate} from './certificate';

export interface AddressAlternative {

  idAddressAlternative?: number;
  linkAddressAlternative?: string;
  certificate?: Certificate;

}

export class AddressAlternative {

  public idAddressAlternative?: number;
  public linkAddressAlternative?: string;
  public certificate?: Certificate;

  constructor(idAddressAlternative: number,
              linkAddressAlternative: string,
              certificate: Certificate) {

    this.idAddressAlternative = idAddressAlternative;
    this.linkAddressAlternative = linkAddressAlternative;
    this.certificate = certificate;

  }
}
