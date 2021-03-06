import socket
from http.server import HTTPServer, SimpleHTTPRequestHandler

class HTTPServerV6(HTTPServer):
  address_family = socket.AF_INET6

def main():
  server = HTTPServerV6(('::', 80), SimpleHTTPRequestHandler)
  server.serve_forever()

if __name__ == '__main__':
  main()

