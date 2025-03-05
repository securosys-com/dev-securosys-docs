# usage: ./sign_timestamp_ec.sh <role>
TIMESTAMP=$(date -u "+%Y-%m-%dT%H:%M:%S+00:00")

echo Fetching timestamp
echo Creating signed timestamp

PRIVATE_KEY_FILENAME=$1
TSB_DEMO_TIMESTAMP_CERTIFICATE="MIIFJTCCAw2gAwIBAgIUJyZ+70EZshas5hekGw6WTqAc/uowDQYJKoZIhvcNAQELBQAwIjEgMB4GA1UEAwwXcHJpbXVzZGV2LmNsb3Vkc2hzbS5jb20wHhcNMjQwMjAxMDg1MjU2WhcNMzQwMTI5MDg1MjU2WjAiMSAwHgYDVQQDDBdwcmltdXNkZXYuY2xvdWRzaHNtLmNvbTCCAiIwDQYJKoZIhvcNAQEBBQADggIPADCCAgoCggIBAKNKukSs6g+nlVrXKc+TkpCYml0vZQ2OaWwAoC32XIkR+/URf1X2Q+WMPjJkg7bl2VW3SL4GFmohDJm6hag2QBBpgFyCaEQ7g8QoQoopp+tPiCPGTRVYIVsGHctUhcX01SigQ1fYVCinbTdmWEVcGhkT1fFND5sEhgwsMQGDJTg0eKBlVxs6AF/+mMoQxQCdCjUiHzvqlylDgT70RwLbmZy4amTdInzK6VvfW+W5+l3rABNxgnC9fyr2/dAa9SYBApNh8V9098BXEMplyk0AERgLrrcOt4PdxdM/VLLBSmd4VINW3GU467WMvL90lNxGHq3Zi1zievIb7A2Y6vZcsYUAQYOAb5WaUkCl1WtWBxMK9ecrCorqU38PkH9eP3d1NgDnBqZjs58taItLTl/iCuqT9xrAvNu+nSeYvCn5zbgYpmlFx7/nD8GKcKYX9HpiOXvLx3mX6UJczx/Ey7g6OyWSrHPEcE+vcZCtvVrggSzSDrbG0xmF5Z4eyViIm7Q1ZZ3OiZHn8AevBgigwQ5P/lqheS5GrpnnC6oVMWLk5DL0HCZJc3vhd8RjuIWbnO1Sl3Gi3uR97dUbhfpi6Lga23APKRIGo8D2VqrBomgF6WP0wuleVk6y8SoHes27xlYJx2pwpeGp116ZttijJ+aMCIRgeX0QTBH4UENqp/IE7XfRAgMBAAGjUzBRMB0GA1UdDgQWBBQ1c7jH3yKX9xl35nD2Q9KQF1KzNzAfBgNVHSMEGDAWgBQ1c7jH3yKX9xl35nD2Q9KQF1KzNzAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4ICAQCZ0SOpweNCSUdrFgL15CpwWJfb84EmQ5fx8gLxLpCYKywV+Eh3JpPICPubP0zC1FWPiX9Hiy86orv9OOn5kbEcDn9wEa2452V/tzSK6evTW+/jpY4di+xdqzqPfK5BEKlJ6I199MTT6Wco4KOmwN2KEpojsOM3MjytoDaGwBjRe8jd757P4t6beDMvpwaimlXpjsK9IaQAH2aFQjraDILlcn+T8doZpIvyt77XAn1xJVhT/Su07BKe9/CyHRo9bexxMuFtXX3fw/3KtJM8LgxLDRSEq1jOao4iDA96bij/3E9Nwx1JtusGVge1HMyYZITbQDNmSTz5RR5tN0oUg9shB74hTeI+qNIEQ/MW/kiG0TEL+AY3nu1XfVX/7s75XIBQMwHrjsdYR5J7qi8pgfwVQ5AAjkHA/HdEX5DGIWQoqrKhIPTyLnxzFDCIubLyt2cF23slrTsnulhmCaXrWHoKTYdFMo2wzV25ABUJUJoCZSxgtXjVPkM8VcKJk2AnD3sv9EOkx+U/syzvv6amduRLF5eDgxTPn/O7Gh+WKgify9KvbHFbEPkk3Xq0zv26sWGRDzGe20zxpFeYOaibDkJ/5Ymv2/RHjMYALBMaHixru0PnFSER/+akQxjl0xPoEyJ9GkhulVcPaawyP6aJsIDnOd/hSZLS7wT+H3tvC4pY4A=="
SIGNATURE=$(echo -n $TIMESTAMP | openssl dgst -sha256 -sign $PRIVATE_KEY_FILENAME | openssl enc -base64 | tr -d '\n')

echo Creating TSB request payload
echo
echo '{
  "timestamp": "'$TIMESTAMP'",
  "timestampSignature": "'$SIGNATURE'",
  "timestampSigningCertificate": "'$TSB_DEMO_TIMESTAMP_CERTIFICATE'",
  "timestampDigestAlgorithm": "SHA-256",
  "detailLevel": "level1",
  "paging": {
    "pageNumber": 0,
    "pageSize": 1,
    "sortOrder": "CREATION_DATE_ASC"
  }
}'
echo
echo
echo "Success, now send the output above to: POST /v1/filteredSignApprovalTask"
echo
