import { TestBed } from '@angular/core/testing';

import { CertificateDataService } from './certificate-data.service';

describe('CertificateDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CertificateDataService = TestBed.get(CertificateDataService);
    expect(service).toBeTruthy();
  });
});
