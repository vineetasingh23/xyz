import React, { useState } from 'react';

const App = () => {
  const [theme, setTheme] = useState('light');

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === 'light' ? 'dark' : 'light'));
  };

  const getBackgroundColor = () => {
    return theme === 'light' ? '#ffffff' : '#121212'; // Light and dark theme background colors
  };

  const getTextColor = () => {
    return theme === 'light' ? '#000000' : '#ffffff'; // Text color based on theme
  };

  const getRandomColor = () => {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  };

  const buttonStyle = {
    backgroundColor: getRandomColor(), // Random color for the button background
    color: getTextColor(), // Text color based on theme
    border: 'none',
    padding: '10px 20px',
    cursor: 'pointer',
    borderRadius: '5px', // Rounded corners for the button
    boxShadow: '0px 2px 5px rgba(0, 0, 0, 0.2)', // Box shadow for a raised effect
    transition: 'background-color 0.3s ease', // Smooth transition for background color change
  };

  const toggleButtonStyle = {
    backgroundColor: '#f0f0f0', // Background color for the toggle button
    borderRadius: '20px', // Rounded shape for the toggle button
    width: '50px',
    height: '24px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: theme === 'light' ? 'flex-start' : 'flex-end', // Align toggle button based on theme
    padding: '2px',
    boxSizing: 'border-box',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease', // Smooth transition for background color change
  };

  return (
    <div style={{ backgroundColor: getBackgroundColor(), height: '100vh', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
      <div style={{ textAlign: 'center' }}>
        <h1 style={{ color: getTextColor() }}>Theme Toggle</h1>
        <div style={toggleButtonStyle} onClick={toggleTheme}>
          <div style={{ width: '20px', height: '20px', borderRadius: '50%', backgroundColor: '#ffffff', boxShadow: '0px 2px 5px rgba(0, 0, 0, 0.2)', transition: 'transform 0.3s ease', transform: theme === 'light' ? 'translateX(0px)' : 'translateX(26px)' }}></div>
        </div>
      </div>
    </div>
  );
};

export default App;
