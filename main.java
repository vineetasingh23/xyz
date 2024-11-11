import CreateStreams from '../../pages/create-streams';
import { screen, render, fireEvent } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import { SnackbarProvider } from 'notistack';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { APIEndPoints } from '../../common/APIEndPoints';

const eSnack = document.createElement('div');
let mock = new MockAdapter(axios);
window.navigator.sendBeacon = jest.fn();

const MockCreateWorkflow = () => {
  return (
    <SnackbarProvider maxSnack={5} autoHideDuration={3000} preventDuplicate domRoot={eSnack}>
      <Router>
        <CreateStreams />
      </Router>
    </SnackbarProvider>
  );
};

jest.mock('@react-pdf-viewer/core', () => ({
  Worker: jest.fn(() => null),
  Viewer: jest.fn(() => null),
}));

// Shim for ResizeObserver and DOMMatrixReadOnly
class ResizeObserver {
  callback: globalThis.ResizeObserverCallback;

  constructor(callback: globalThis.ResizeObserverCallback) {
    this.callback = callback;
  }

  observe(target: Element) {
    this.callback([{ target }] as globalThis.ResizeObserverEntry[], this);
  }

  unobserve() {}

  disconnect() {}
}

class DOMMatrixReadOnly {
  m22: number;
  constructor(transform: string) {
    const scale = transform?.match(/scale\(([1-9.])\)/)?.[1];
    this.m22 = scale !== undefined ? +scale : 1;
  }
}

// Only run the shim once when requested
let init = false;

export const mockReactFlow = () => {
  if (init) return;
  init = true;

  global.ResizeObserver = ResizeObserver;
  global.DOMMatrixReadOnly = DOMMatrixReadOnly;

  Object.defineProperties(global.HTMLElement.prototype, {
    offsetHeight: {
      get() {
        return parseFloat(this.style.height) || 1;
      },
    },
    offsetWidth: {
      get() {
        return parseFloat(this.style.width) || 1;
      },
    },
  });
  (global.SVGElement as any).prototype.getBBox = () => ({
    x: 0,
    y: 0,
    width: 0,
    height: 0,
  });
};

describe('Test cases for create-streams page', () => {
  beforeEach(() => {
    mockReactFlow();
    render(<MockCreateWorkflow />);
  });

  it('check for the text visible in the screen', () => {
    expect(screen.findByText('Create Stream')).toBeDefined();
    expect(screen.findByText('Stream Details')).toBeDefined();
    expect(screen.findByText('Node Panel')).toBeDefined();
    expect(screen.findByText('Custom Document Classifier')).toBeDefined();
    expect(screen.findByText('Custom Document Extractor')).toBeDefined();
    expect(screen.findByText('Reset')).toBeDefined();
    expect(screen.findByText('You can drag these nodes to the pane on the top.')).toBeDefined();
  });

  it('check the functionality of submit button', async () => {
    const submitButton = screen.getByTestId('submitButton');
    fireEvent.click(submitButton);
    await screen.findByText('Please fill the required fields!');
  });

  it('check the functionality of submit API with error message', async () => {
    const submitButton = screen.getByTestId('submitButton');
    const tenantID = screen.getByTestId('tenantID');
    fireEvent.change(tenantID, { target: { value: '1234_dev' } });
    const streamName = screen.getByTestId('streamName');
    fireEvent.change(streamName, { target: { value: 'stream' } });

    mock.onPost(APIEndPoints['ROOT'] + APIEndPoints['SAVE_WORKFLOW']).reply(function (config) {
      return [404];
    });
    fireEvent.click(submitButton);
  });

  it('check the functionality of submit API: Failed Response', async () => {
    const submitButton = screen.getByTestId('submitButton');
    const tenantID = screen.getByTestId('tenantID');
    fireEvent.change(tenantID, { target: { value: '1234_dev' } });
    const streamName = screen.getByTestId('streamName');
    fireEvent.change(streamName, { target: { value: 'stream' } });

    mock.onPost(APIEndPoints['ROOT'] + APIEndPoints['SAVE_WORKFLOW']).reply(function (config) {
      return [400, { error: 'Request Failed' }];
    });
    fireEvent.click(submitButton);
  });

  it('check the functionality of submit API: Successful response', async () => {
    const submitButton = screen.getByTestId('submitButton');
    const tenantID = screen.getByTestId('tenantID');
    fireEvent.change(tenantID, { target: { value: '1234_dev' } });
    const streamName = screen.getByTestId('streamName');
    fireEvent.change(streamName, { target: { value: 'stream' } });

    mock.onPost(APIEndPoints['ROOT'] + APIEndPoints['SAVE_WORKFLOW']).reply(function (config) {
      return [200, '111111111111'];
    });
    fireEvent.click(submitButton);
  });

  it('check the functionality of reset button', async () => {
    const tenantID = screen.getByTestId('tenantID');
    fireEvent.change(tenantID, { target: { value: '1234_dev' } });
    const streamName = screen.getByTestId('streamName');
    fireEvent.change(streamName, { target: { value: 'stream' } });
    const resetButton = screen.getByTestId('resetButton');
    fireEvent.click(resetButton);
    await screen.findByText('Tenant ID');
  });
});
