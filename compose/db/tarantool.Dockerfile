FROM debian:12.5

ARG TARANTOOL_USER_PASSWORD

RUN useradd --user-group --create-home --no-log-init --shell /bin/bash tarantool \
    && echo "tarantool:$TARANTOOL_USER_PASSWORD" | chpasswd

RUN apt update \
    && apt install --no-install-suggests ca-certificates curl cmake gnupg2 -y \
    && curl -L https://download.tarantool.org/tarantool/release/series-3/gpgkey | apt-key add - \
    && curl -L https://download.tarantool.org/tarantool/modules/gpgkey | apt-key add -  \
    && echo "deb https://download.tarantool.org/tarantool/release/series-3/linux-deb/ static main" >> /etc/apt/sources.list.d/tarantool_3.list \
    && echo "deb https://download.tarantool.org/tarantool/release/modules/linux-deb/ static main" >> /etc/apt/sources.list.d/tarantool_3.list \
    && apt update \
    && apt install tarantool -y \
    && apt install tarantool-dev -y \
    && apt install tt -y \
    && apt clean \
    && apt -y autoremove \
    && rm -rf /var/lib/apt/lists/

COPY /tarantool/tt.yaml /etc/tarantool
COPY /tarantool/saas-dialogue /etc/tarantool/instances.enabled/saas-dialogue

RUN cd /etc/tarantool/instances.enabled/saas-dialogue \
        && tt build

EXPOSE 3301

#CMD cd /etc/tarantool/instances.enabled/saas-dialogue \
#    && tt start