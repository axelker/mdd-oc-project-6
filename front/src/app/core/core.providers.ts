import { EnvironmentProviders, makeEnvironmentProviders } from '@angular/core';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

export function provideCore(): EnvironmentProviders {
  return makeEnvironmentProviders([
    provideHttpClient(withInterceptorsFromDi())
  ]);
}
