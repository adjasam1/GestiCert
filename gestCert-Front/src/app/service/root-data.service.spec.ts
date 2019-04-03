import { TestBed } from '@angular/core/testing';

import { RootDataService } from './root-data.service';

describe('RootDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RootDataService = TestBed.get(RootDataService);
    expect(service).toBeTruthy();
  });
});
