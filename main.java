import React from 'react'
import { render, screen, fireEvent } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'
import { withSnackBarHook } from '../path/to/withSnackBarHook'
import CreateStreams from '../path/to/CreateStreams'
import APIConfig from '../path/to/APIConfig'
import ErrorConstants from '../path/to/ErrorConstants'
import SuccessConstants from '../path/to/SuccessConstants'

// Wrap component with Snackbar hook
const MockCreateStreams = withSnackBarHook(CreateStreams)

describe('CreateStreams Component', () => {
  
  beforeEach(() => {
    jest.clearAllMocks()
  })

  test('renders CreateStreams component correctly', () => {
    render(<MockCreateStreams />)
    expect(screen.getByText('Signature Capture')).toBeInTheDocument()
  })

  test('validates empty tenantId and streamName in validationOfParams', () => {
    const { container } = render(<MockCreateStreams />)
    const instance = container.firstChild as any
    
    instance.setState({ tenantId: '', streamName: '' })
    const isValid = instance.validationOfParams()

    expect(isValid).toBe(false)
    expect(screen.getByText(ErrorConstants.EMPTY_FIELDS)).toBeInTheDocument()
  })

  test('showMsg displays success message correctly', () => {
    render(<MockCreateStreams />)
    
    const instance = screen.getByTestId('component-instance') // Assume there’s a way to access the component instance
    instance.showMsg(SuccessConstants.STREAMS_REQUEST_SUCCESSFUL, 'success')
    
    expect(screen.getByText(SuccessConstants.STREAMS_REQUEST_SUCCESSFUL)).toBeInTheDocument()
  })

  test('fetchStream calls API and updates state on success', async () => {
    const mockFetchStreamResponse = { workflowName: 'Test Stream', tenantId: '1234', otherData: {} }
    
    jest.spyOn(APIConfig, 'getCall').mockResolvedValue({ data: mockFetchStreamResponse })
    
    render(<MockCreateStreams />)
    
    const instance = screen.getByTestId('component-instance') // Update based on actual structure
    
    await instance.fetchStream('test-id')
    
    expect(instance.state.streamName).toBe('Test Stream')
    expect(instance.state.streamTenantId).toBe('1234')
  })

  test('saveStreamsForLater displays error on API failure', async () => {
    jest.spyOn(APIConfig, 'postCall').mockRejectedValue({ response: { data: { error: 'Failed to save stream' } } })
    
    render(<MockCreateStreams />)
    const instance = screen.getByTestId('component-instance')
    
    await instance.saveStreamsForLater()
    
    expect(screen.getByText('Failed to save stream')).toBeInTheDocument()
  })

  test('changeToEditMode updates view mode correctly', () => {
    render(<MockCreateStreams />)
    const instance = screen.getByTestId('component-instance')
    
    instance.setState({ tenantId: 'testTenant', currentStreamId: 'testStream', streamTenantId: 'testTenant' })
    instance.changeToEditMode()
    
    expect(instance.state.isViewMode).toBe(false)
  })

  test('validationOfParams fails when tenantId format is invalid', () => {
    render(<MockCreateStreams />)
    const instance = screen.getByTestId('component-instance')
    
    instance.setState({ tenantId: 'invalid-id', streamName: 'StreamName' })
    
    const isValid = instance.validationOfParams()
    expect(isValid).toBe(false)
    expect(screen.getByText(ErrorConstants.ENTER_TENANT_ID)).toBeInTheDocument()
  })
})
