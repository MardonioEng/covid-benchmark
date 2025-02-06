import 'bootstrap/dist/css/bootstrap.min.css';

export const metadata = {
  title: 'Planisa Challenge',
  description: 'COVID-19 Benchmark',
};

export default function RootLayout({ children }) {
  return (
    <html lang="pt-BR">
      <body suppressHydrationWarning={true}>
        <nav className="navbar navbar-dark bg-dark">
          <div className="container">
            <span className="navbar-brand mb-0 h1">Planisa Challenge</span>
          </div>
        </nav>
        {children}
      </body>
    </html>
  );
}
