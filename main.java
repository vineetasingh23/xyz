<button
        style={{
          padding: '8px 16px',
          backgroundColor: '#007bff',
          color: '#fff',
          border: 'none',
          borderRadius: '4px',
          cursor: 'pointer',
          marginRight: '8px', // Add margin-right for spacing
          transition: 'background-color 0.3s ease',
        }}
        onClick={toggleBcc}
        onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = '#0056b3')} // Hover color
        onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = '#007bff')} // Original color
      >
