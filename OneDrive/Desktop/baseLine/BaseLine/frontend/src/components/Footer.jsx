const Footer = () => {
  const currentYear = new Date().getFullYear();

  return (
    <footer className="bg-surface border-t border-surface mt-auto flex-shrink-0 sticky bottom-0">
      <div className="container mx-auto px-4 py-3 md:py-4">
        <div className="flex flex-col md:flex-row justify-between items-center gap-4">
          <div className="text-text/60 text-xs md:text-sm">
            © {currentYear} BaseLine. All rights reserved.
          </div>
          <div className="text-text/60 text-xs md:text-sm">
            Your personal finance & life tracker
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;

