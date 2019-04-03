import { TestBed } from '@angular/core/testing';

import { EnvironmentDataService } from './environment-data.service';

describe('EnvironmentDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnvironmentDataService = TestBed.get(EnvironmentDataService);
    expect(service).toBeTruthy();
  });
});
