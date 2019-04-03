import {Certificate} from './certificate';

export class Server {

  constructor(public idServer: number,
              public nameServer: string,
              public certificates: Certificate[]) {}

}
